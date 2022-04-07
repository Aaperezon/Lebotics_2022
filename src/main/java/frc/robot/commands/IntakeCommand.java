package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase 
{
  private final IntakeSubsystem m_subsystem;
  private double  lower_value, max_speed;
  private double lift_value;
  private boolean intake_on, intake_off;
  private AutonomousDriveCommand autoIntake;
  private boolean auto_intake_scheduled;
  public IntakeCommand(IntakeSubsystem subsystem ) 
  {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    max_speed = 1;
    this.autoIntake = autoIntake;
    auto_intake_scheduled = false;
    SmartDashboard.putBoolean("Auto Intake", false);

  }

  @Override
  public void initialize() {}

  @Override
  public void execute() 
  {
    lift_value = Constants.driver2.getRightTriggerAxis();
    lower_value = Constants.driver2.getLeftTriggerAxis();

    if(lift_value >= .1){
      m_subsystem.lift(lift_value*max_speed);
    }
    else if(lower_value >= .1){
      m_subsystem.lower(lower_value*max_speed);
    }
    else{
      m_subsystem.stop_servo();
    }

    // double take_ball = Constants.driver1.getRightTriggerAxis();

    
    if(Constants.driver1.getXButton()){
          m_subsystem.backward();
        }
        else if(Constants.driver1.getBButton()){
          m_subsystem.forward();
        }
        else{
          m_subsystem.stop_motor();
        }
    

    // if(intake_on) {
    //   if(auto_intake_scheduled == false){
    //     autoIntake.schedule();
    //     auto_intake_scheduled = true;
    //   }
    //   SmartDashboard.putBoolean("Auto Intake", true);
    // }else{
    //   if(auto_intake_scheduled == true){
    //     autoIntake.restartTimer();
    //     autoIntake.cancel();
    //     auto_intake_scheduled = false;
    //   }
    //   SmartDashboard.putBoolean("Auto Intake", false);
    //   if(Constants.driver1.getXButton()){
    //     m_subsystem.backward();
    //   }
    //   else if(Constants.driver1.getBButton()){
    //     m_subsystem.forward();
    //   }
    //   else{
    //     m_subsystem.stop_motor();
    //   }
    // }
    // if (Constants.driver1.getAButton()) {
    //   if (!intake_off) {
    //     intake_on = !intake_on;
    //     intake_off = true;
    //   }
    // }
    // else {
    //   intake_off = false;
    // }

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished()
  {
    return false;
  }
}