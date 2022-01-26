package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ExampleSubsystem2;

public class ExampleCommand2 extends CommandBase {
  final ExampleSubsystem2 m_subsystem;
  long start_time, end_time, current_time, target_time;
  double speed;
  boolean terminate;
  public ExampleCommand2(ExampleSubsystem2 subsystem, long target_time, double speed) {
    m_subsystem = subsystem;
    addRequirements(m_subsystem);
    this.target_time = target_time;
    this.speed = speed;
    terminate = false;
  }

  @Override
  public void initialize() {
    start_time = System.currentTimeMillis();
  }

  @Override
  public void execute() {
    end_time = System.currentTimeMillis();
    current_time = end_time - start_time;
    if(current_time < target_time){
      m_subsystem.runBackward(speed);
    }else{
      terminate = true;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return terminate;
  }
}
