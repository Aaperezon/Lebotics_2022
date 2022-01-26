package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ExampleSubsystem1;
import frc.robot.subsystems.ExampleSubsystem2;

public class AutonomousCommandGroup extends SequentialCommandGroup  {
  public AutonomousCommandGroup(ExampleSubsystem1 exampleSubsystem1, ExampleSubsystem2 exampleSubsystem2) {
    addCommands(
      new ExampleCommand1(exampleSubsystem1, 2000, .3),
      new ExampleCommand2(exampleSubsystem2, 4000, .6)
    );
  }
}
