// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberCommand extends CommandBase {
  ClimberSubsystem m_subsystem;

  public ClimberCommand(ClimberSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    // m_subsystem.move(Constants.driver2.getLY());
    m_subsystem.moveLeft(Constants.driver2.getLeftY());
    m_subsystem.moveRight(Constants.driver2.getRightY());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
