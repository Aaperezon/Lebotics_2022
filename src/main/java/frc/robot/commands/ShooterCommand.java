// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ShooterCommand extends CommandBase {
  private final ShooterSubsystem m_subsystem;
  boolean camOn = false;
  private PIDController shooter_pid;
  boolean camOff = false;

  private boolean shoot_on, shoot_off;
  public ShooterCommand(ShooterSubsystem subsystem) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    shooter_pid = new PIDController(.01, 0, 0);
    shooter_pid.reset();
    m_subsystem.resetEncoder();

  }

  @Override
  public void initialize() {
    shooter_pid.reset();
    m_subsystem.resetEncoder();
   
  }

  @Override
  public void execute() {
    
    
    if(shoot_on) {
      double shooter_speed = shooter_pid.calculate(m_subsystem.getRPM(), m_subsystem.getTargetRPM());
      m_subsystem.startEngine(-shooter_speed);
      SmartDashboard.putBoolean("Action", true);
      System.out.println(-shooter_speed);
    }else{
      m_subsystem.stop();
      SmartDashboard.putBoolean("Action", false);
      shooter_pid.reset();
      m_subsystem.resetEncoder();
    }
    if (Constants.driver2.getBButton() == true) {
      if (!shoot_off) {
        shoot_on = !shoot_on;
        shoot_off = true;
      }
    }
    else {
      shoot_off = false;
    }
    //set RPM for PID
    if(Constants.driver2.getPOV() == 0){
      m_subsystem.setMoreTargetRPM();
    }
    else if(Constants.driver2.getPOV() == 180){
      m_subsystem.setLessTargetRPM();
    }
    else{
      m_subsystem.canModify();
    }

    SmartDashboard.putNumber("Target", m_subsystem.getTargetRPM());
    SmartDashboard.putNumber("RPM", m_subsystem.getRPM());
    //SHOOT
    m_subsystem.shoot(Constants.driver2.getLeftBumper());


    // m_subsystem.setkP(.02); 
    // m_subsystem.setkI(0); 
    // m_subsystem.setkD(0); 
    
    


    // // MANUAL USE
    // //Shoot with B
    // if(Constants.driver2.getBButton()){
    //   m_subsystem.startEngine();
    // }else{
    //   m_subsystem.stop();
    // }


    
    // //SpeedUp
    // if(Constants.driver2.getPOV() == 0){
    //   m_subsystem.speedUp();
    // }
    // else if(Constants.driver2.getPOV() == 180){
    //   m_subsystem.speedDown();
    // }
    // else{
    //   m_subsystem.canModify();
    // }
    

    //Change camera mode: target/nomal view
    if(camOn) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }else{
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    }
    if (Constants.driver2.getStartButton()) {
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
