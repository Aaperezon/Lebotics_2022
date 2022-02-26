// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutonomousShoot extends ParallelCommandGroup {
  public AutonomousShoot(ChassisSubsystem chassisSubsystem, ShooterSubsystem shooterSubsystem) {
    addCommands(new AimCommand(chassisSubsystem), new ShootCommand(shooterSubsystem));
  }
}
