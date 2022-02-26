// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShooterCommand extends CommandBase {
  private final ShooterSubsystem m_subsystem;
  boolean camOn = false;
  boolean camOff = false;

  private boolean shoot_on, shoot_off;
  public ShooterCommand(ShooterSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
  }

  @Override
  public void initialize() {
   
  }

  @Override
  public void execute() {

    
    // if(shoot_on) {
    //   m_subsystem.shoot();
    //   SmartDashboard.putBoolean("Action", true);
    // }else{
    //   m_subsystem.stop();
    //   SmartDashboard.putBoolean("Action", false);
    // }
    // if (Constants.driver2.getB() == true) {
    //   if (!shoot_off) {
    //     shoot_on = !shoot_on;
    //     shoot_off = true;
    //   }
    // }
    // else {
    //   shoot_off = false;
    // }
    // //set RPM for PID
    // if(Constants.driver2.getPOV() == 0){
    //   m_subsystem.setMoreTarget();
    // }
    // else if(Constants.driver2.getPOV() == 180){
    //   m_subsystem.setLessTarget();
    // }
    // else{
    //   m_subsystem.canModify();
    // }

    SmartDashboard.putNumber("Target", m_subsystem.getTarget());
    SmartDashboard.putNumber("RPM", m_subsystem.getRPM());


    // m_subsystem.setkP(.02); 
    // m_subsystem.setkI(0); 
    // m_subsystem.setkD(0); 
    
    


    // MANUAL USE
    //Shoot with B
    if(Constants.driver2.getB()){
      m_subsystem.startEngine();
    }else{
      m_subsystem.stop();
    }

    m_subsystem.shoot(Constants.driver2.getLB());

    
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


    //Change camera mode: target/nomal view
    if(camOn) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }else{
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    }
    if (Constants.driver2.getSTART()) {
      if (!camOff) {
        camOn = !camOn;
        camOff = true;
      }
    }
    else {
      camOff = false;
    }

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
