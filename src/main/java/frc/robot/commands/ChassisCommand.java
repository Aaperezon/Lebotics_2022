package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ChassisSubsystem;

public class ChassisCommand extends CommandBase {
  ChassisSubsystem m_subsystem;
  public ChassisCommand(ChassisSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);

  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double throttle = -Constants.driver1.getLeftY();
    double throttle_turn = Constants.driver1.getRightX();
    boolean left = Constants.driver1.getLeftBumper();
    boolean right = Constants.driver1.getRightBumper();
    m_subsystem.drive(throttle, throttle_turn, left, right);
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
