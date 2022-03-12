package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutonomousDriveCommand extends CommandBase 
{
  private PIDController left_drive_pid,right_drive_pid;
  ChassisSubsystem chassis_subsystem;
  IntakeSubsystem intake_subsystem;
  ShooterSubsystem shooter_subsystem;
  private double target;
  private String mode;
  private boolean terminate;
  private PIDController aim_pid;
  private long start_time, end_time, current_time, target_time;
  public AutonomousDriveCommand(ChassisSubsystem chassisSubsystem, double target, String mode) 
  {
    this.chassis_subsystem = chassisSubsystem;
    addRequirements(chassisSubsystem);
    this.target = target;
    this.mode = mode;
    left_drive_pid = new PIDController(0, 0, 0);
    right_drive_pid = new PIDController(0, 0, 0);
   
    terminate = false;
    left_drive_pid.reset();
    right_drive_pid.reset();

    chassisSubsystem.resetEncoders();
    start_time = System.currentTimeMillis();

  }
  public AutonomousDriveCommand(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooterSubsystem){
    this.chassis_subsystem = chassis_subsystem;
    this.shooter_subsystem = shooterSubsystem;
    addRequirements(chassis_subsystem);
    this.mode = "shoot";
    left_drive_pid = new PIDController(0, 0, 0);
    right_drive_pid = new PIDController(0, 0, 0);
   
    terminate = false;
    left_drive_pid.reset();
    right_drive_pid.reset();

    chassis_subsystem.resetEncoders();
    start_time = System.currentTimeMillis();
    aim_pid = new PIDController(.017, 0, 0);

  }
  public AutonomousDriveCommand(ChassisSubsystem chassis_subsystem,ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) 
  {
    this.shooter_subsystem = shooterSubsystem;
    this.chassis_subsystem = chassis_subsystem;
    this.intake_subsystem = intakeSubsystem;
    addRequirements(chassis_subsystem);
    this.target = 0;
    this.mode = "take_ball";
    left_drive_pid = new PIDController(0, 0, 0);
    right_drive_pid = new PIDController(0, 0, 0);
   
    terminate = false;
    left_drive_pid.reset();
    right_drive_pid.reset();

    chassis_subsystem.resetEncoders();
    start_time = System.currentTimeMillis();

  }

  @Override
  public void initialize() 
  {
    left_drive_pid.reset();
    right_drive_pid.reset();
    terminate = false;
    chassis_subsystem.resetEncoders();
    start_time = System.currentTimeMillis();

  }
  public void restartTimer(){
    start_time = System.currentTimeMillis();
  }

  @Override
  public void execute() 
  {
    double left_distance = chassis_subsystem.getLeftDistance();
    double right_distance = chassis_subsystem.getRightDistance();

    if(mode == "angle")
    {
      left_drive_pid.setP(.038);
      left_drive_pid.setI(0);
      left_drive_pid.setD(.0013);
      // right_drive_pid.setP(.017);
      // right_drive_pid.setI(0);
      // right_drive_pid.setD(0);
      
      double target_aux = target / 8;
      double left_chassis_speed = left_drive_pid.calculate(left_distance, target_aux);
      // double right_chassis_speed = right_drive_pid.calculate(right_distance, -target_aux);
      chassis_subsystem.drive(left_chassis_speed, -left_chassis_speed);
      // System.out.println("ANGLE: "+target_aux+"  "+left_distance+"  "+right_distance+" || "+left_chassis_speed);
      if( target_aux > 0 && left_distance > target_aux)
        terminate = true;
      else if(target_aux < 0 && left_distance < target_aux )
        terminate = true;
    }
    
    else if(mode == "distance")
    {
      int error = 10;
      left_drive_pid.setP(.027);
      left_drive_pid.setI(0);
      left_drive_pid.setD(0);

      double chassis_speed = left_drive_pid.calculate(left_distance, target);
      chassis_subsystem.drive(chassis_speed, chassis_speed);
      // System.out.println("DISTANCE:   "+left_distance+"  "+right_distance+" || "+chassis_speed);
      if( target > 0 && left_distance > target - error ){
        terminate = true;
      }
      else if(target < 0 && left_distance < target + error){
        terminate = true;
      }
    }else if(mode == "take_ball"){
      shooter_subsystem.setAngle(Constants.camera_objective_ball);

      String gameData = DriverStation.getGameSpecificMessage();
      if(gameData.length() > 0){
        // System.out.println(gameData.charAt(0));
        switch (gameData.charAt(0))
        {
          case 'B' :
            //Get BLUE BALL
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setDouble(2);
            break;
          case 'R' :
            //Get RED BALL
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setDouble(1);
            break;
          default :
            return;
        }
      }
      left_drive_pid.setP(.017);
      left_drive_pid.setI(0);
      left_drive_pid.setD(0);
      double x_target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
      double area_target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
      double chassis_speed = left_drive_pid.calculate(x_target, 0);
      // System.out.println(speed);
      chassis_subsystem.drive(.4-chassis_speed, .4+chassis_speed);

      end_time = System.currentTimeMillis();
      current_time = end_time - start_time;
      System.out.println(current_time);
      if(current_time < 1000){
        intake_subsystem.lower(.7);
      }
      intake_subsystem.backward(1);


      // System.out.println("GETTING BALL:   "+left_distance+"  "+right_distance+" || "+chassis_speed + " time: "+current_time);
      if(area_target >= 4){
        terminate = true;

      }
   
    }
    else if(mode == "shoot"){
      shooter_subsystem.setAngle(Constants.camera_objective_reflective);

      // NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setDouble(0);
      end_time = System.currentTimeMillis();
      current_time = end_time - start_time;
      if(current_time > 2000){
        //SHOOT
        shooter_subsystem.shoot(true, false);
      }
      //AIM WITH CHASSIS
      // double x_target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
      // double chassis_speed = aim_pid.calculate(x_target, 0);
      // chassis_subsystem.drive(-chassis_speed, chassis_speed);
      //REV ENGINE
      double shooter_speed = -.62;
      shooter_subsystem.startEngine(shooter_speed);
      SmartDashboard.putNumber("Target", shooter_speed);

      if(current_time > 5000){
        terminate = true;
        shooter_subsystem.stop();
        shooter_subsystem.shoot(false,false);
      }

     
    }
    
  }

  @Override
  public void end(boolean interrupted) 
  {
    // System.out.println("Termino");
    chassis_subsystem.drive(0, 0);
  }

  @Override
  public boolean isFinished() 
  {
    return terminate;
  }
}