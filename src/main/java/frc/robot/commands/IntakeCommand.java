package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
  private final IntakeSubsystem m_subsystem;
  private double lift_value, lower_value, max_speed;
  public IntakeCommand(IntakeSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    max_speed = 1;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    lift_value = Constants.driver2.getRTRIGGER();
    lower_value = Constants.driver2.getLTRIGGER();
    if(lift_value >= .1){
      m_subsystem.lift(lift_value*max_speed);
    }
    else if(lower_value >= .1){
      m_subsystem.lower(lower_value*max_speed);
    }else{
      m_subsystem.stop_servo();
    }
    
    if(Constants.driver1.getRB()){
      m_subsystem.backward();
    }else if(Constants.driver1.getLB()){
      m_subsystem.forward();
    }else{
      m_subsystem.stop_motor();
    }

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
