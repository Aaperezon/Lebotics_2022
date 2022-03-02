package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ChassisSubsystem;

public class AutonomousDriveCommand extends CommandBase 
{
  private PIDController left_drive_pid,right_drive_pid;
  ChassisSubsystem m_subsystem;
  private double target;
  private String mode;
  private boolean terminate;
  public AutonomousDriveCommand(ChassisSubsystem subsystem, double target, String mode) 
  {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    this.target = target;
    this.mode = mode;
    left_drive_pid = new PIDController(0, 0, 0);
    right_drive_pid = new PIDController(0, 0, 0);
   
    terminate = false;
    left_drive_pid.reset();
    right_drive_pid.reset();
    m_subsystem.resetEncoders();
  }

  @Override
  public void initialize() 
  {
    left_drive_pid.reset();
    right_drive_pid.reset();
    terminate = false;
    m_subsystem.resetEncoders();
  }

  @Override
  public void execute() 
  {
    double left_distance = m_subsystem.getLeftDistance();
    double right_distance = m_subsystem.getRightDistance();

    if(mode == "angle")
    {
      left_drive_pid.setP(.06);
      left_drive_pid.setI(0);
      left_drive_pid.setD(0);
      right_drive_pid.setP(.06);
      right_drive_pid.setI(0);
      right_drive_pid.setD(0);
      
      double target_aux = target * (43.1968 / 90);
      double left_chassis_speed = left_drive_pid.calculate(left_distance, target_aux);
      double right_chassis_speed = right_drive_pid.calculate(right_distance, -target_aux);
      m_subsystem.drive(left_chassis_speed, right_chassis_speed);
      System.out.println("ANGLE: "+target_aux+"  "+left_distance+"  "+right_distance+" || "+left_chassis_speed+"  "+right_chassis_speed);
      if( target_aux > 0 && left_distance > target_aux && right_distance < -target_aux)
        terminate = true;
      else if(target_aux < 0 && right_distance > target_aux && left_distance < -target_aux)
        terminate = true;
    }
    
    else if(mode == "distance")
    {
      left_drive_pid.setP(.03);
      left_drive_pid.setI(0);
      left_drive_pid.setD(0);
      right_drive_pid.setP(.03);
      right_drive_pid.setI(0);
      right_drive_pid.setD(0);

      double left_chassis_speed = left_drive_pid.calculate(left_distance, target);
      double right_chassis_speed = right_drive_pid.calculate(right_distance, target);
      m_subsystem.drive(left_chassis_speed, right_chassis_speed);
      System.out.println("DISTANCE:   "+left_distance+"  "+right_distance+" || "+left_chassis_speed+"  "+right_chassis_speed);
      if( target > 0 && left_distance > target && right_distance > target)
        terminate = true;
      else if(target < 0 && left_distance < target && right_distance < target )
        terminate = true;
    }
    
  }

  @Override
  public void end(boolean interrupted) 
  {
    
    System.out.println("Termino");
    m_subsystem.drive(0, 0);
  }

  @Override
  public boolean isFinished() 
  {
    return terminate;
  }
}