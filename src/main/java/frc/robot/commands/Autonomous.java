package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class Autonomous extends SequentialCommandGroup {
  public Autonomous(ChassisSubsystem chassisSubsystem, ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
    addCommands(
      new AutonomousDriveCommand(chassisSubsystem, -110, "distance"),
      new AutonomousDriveCommand(chassisSubsystem, 90, "distance"),
      new AutonomousDriveCommand(chassisSubsystem, shooterSubsystem)
      
      );
  }
}
