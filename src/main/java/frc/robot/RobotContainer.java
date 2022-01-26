package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.commands.AutonomousCommandGroup;
import frc.robot.subsystems.ExampleSubsystem1;
import frc.robot.subsystems.ExampleSubsystem2;

public class RobotContainer {
  private final ExampleSubsystem1 exampleSubsystem1 = new ExampleSubsystem1(1);
  private final ExampleSubsystem2 exampleSubsystem2 = new ExampleSubsystem2(2);
  private final AutonomousCommandGroup autonomous_command_group = new AutonomousCommandGroup(exampleSubsystem1, exampleSubsystem2);
  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {}

  public CommandGroupBase getAutonomousCommand() {
    return autonomous_command_group;
  }
}
