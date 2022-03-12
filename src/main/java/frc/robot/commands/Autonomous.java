package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Autonomous extends SequentialCommandGroup {
  public Autonomous(ChassisSubsystem chassisSubsystem, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
    addCommands(
      new AutonomousDriveCommand(chassisSubsystem, -55, "distance"),
      new AutonomousDriveCommand(chassisSubsystem, 20, "distance"),
      new AutonomousDriveCommand(chassisSubsystem, shooterSubsystem)
      
      );
  }
}
