// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends CommandBase {
  ShooterSubsystem m_subsystem;
  private PIDController shoot_pid;

  public ShootCommand(ShooterSubsystem subsystem) {
    shoot_pid = new PIDController(.025, 0, 0);
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double y_target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    double speed = shoot_pid.calculate(y_target, 0);
    m_subsystem.shoot(speed);
    SmartDashboard.putNumber("RPM", m_subsystem.getRPM());
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
