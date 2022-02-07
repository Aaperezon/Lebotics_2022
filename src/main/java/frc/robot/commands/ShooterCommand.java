// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShooterCommand extends CommandBase {
  private final ShooterSubsystem m_subsystem;

  public ShooterCommand(ShooterSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {

    //Shoot with B
    if(Constants.driver2.getB()){
      m_subsystem.shoot();
    }else{
      m_subsystem.stop();
    }
    //SpeedUp
    if(Constants.driver2.getPOV() == 0){
      m_subsystem.speedUp();
    }
    else if(Constants.driver2.getPOV() == 180){
      m_subsystem.speedDown();
    }
    else{
      m_subsystem.canModify();
    }





  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
