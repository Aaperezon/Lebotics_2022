// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AimSubsystem;

public class AimCommand extends CommandBase {
  AimSubsystem m_subsystem;
  boolean terminate;
  private PIDController aim_pid;
  NetworkTable table;
  public AimCommand(AimSubsystem subsystem) {
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    table = inst.getTable("limelight-lebotic");

    m_subsystem = subsystem;
    addRequirements(subsystem);
    terminate = false;
    aim_pid = new PIDController(.025, 0, 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NetworkTableEntry x_target = table.getEntry("tx");
    double speed = aim_pid.calculate(x_target.getDouble(0), 0);
    System.out.println(speed);
    m_subsystem.move(-speed, speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return terminate;
  }
}
