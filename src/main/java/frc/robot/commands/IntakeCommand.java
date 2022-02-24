package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
  private final IntakeSubsystem m_subsystem;
  private double  lower_value, max_speed;
  private boolean lift_value;
  public IntakeCommand(IntakeSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    max_speed = 1;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    lift_value = Constants.driver1.getX();
    lower_value = Constants.driver1.getLTRIGGER();
    if(lift_value){
      m_subsystem.lift(1);
    }
    else if(lower_value >= .1){
      m_subsystem.lower(lower_value*max_speed);
    }else{
      m_subsystem.stop_servo();
    }
    double take_ball = Constants.driver1.getRTRIGGER();
    if(take_ball >= .1){
      m_subsystem.forward(take_ball);
    }else if(Constants.driver1.getB()){
      m_subsystem.backward();
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
