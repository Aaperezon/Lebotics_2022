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
  private AutonomousDriveCommand auto_shoot;
  private boolean auto_shoot_scheduled;
  private boolean shoot_on, shoot_off;
  private boolean engine_on, engine_off;
  public ShooterCommand(ShooterSubsystem subsystem,  AutonomousDriveCommand auto_shoot) {
    m_subsystem = subsystem;
    addRequirements(subsystem);
    shooter_pid = new PIDController(.0001, 0, 0);
    shooter_pid.reset();
    m_subsystem.resetEncoder();
    this.auto_shoot = auto_shoot;
    auto_shoot_scheduled = false;
    SmartDashboard.putBoolean("Auto Shoot", false);
    m_subsystem.setCamera();

  }

  @Override
  public void initialize() {
    shooter_pid.reset();
    m_subsystem.resetEncoder();
    m_subsystem.setCamera();
   
  }

  @Override
  public void execute() {
    
    if(engine_on) {
      // double shooter_speed = shooter_pid.calculate(m_subsystem.getRPM(), m_subsystem.getTargetRPM());
      m_subsystem.startEngine(-m_subsystem.getSpeed());
      SmartDashboard.putBoolean("Action", true);
    }else{
      m_subsystem.stop();
      SmartDashboard.putBoolean("Action", false);
      // shooter_pid.reset();
      // m_subsystem.resetEncoder();
    }
    if (Constants.driver2.getBButton() == true) {
      if (!engine_off) {
        engine_on = !engine_on;
        engine_off = true;
      }
    }
    else {
      engine_off = false;
    }
    // //set RPM for PID
    // if(Constants.driver2.getPOV() == 0){
    //   m_subsystem.setMoreTargetRPM();
    // }
    // else if(Constants.driver2.getPOV() == 180){
    //   m_subsystem.setLessTargetRPM();
    // }
    // else{
    //   m_subsystem.canModify();
    // }

    SmartDashboard.putNumber("Target", m_subsystem.getSpeed());
    // SmartDashboard.putNumber("RPM", m_subsystem.getRPM());
    //SHOOT
    m_subsystem.shoot(Constants.driver2.getLeftBumper());


    // // MANUAL USE
    // //Shoot with B
    // if(Constants.driver2.getBButton()){
    //   m_subsystem.startEngine();
    // }else{
    //   m_subsystem.stop();
    // }


    
    //SpeedUp and SpeedDown
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
    if (Constants.driver2.getStartButton()) {
      if (!camOff) {
        camOn = !camOn;
        camOff = true;
      }
    }
    else {
      camOff = false;
    }


    if(shoot_on) {
      if(auto_shoot_scheduled == false){
        auto_shoot.schedule();
        auto_shoot_scheduled = true;
      }
      SmartDashboard.putBoolean("Auto Shoot", true);
    }else{
      if(auto_shoot_scheduled == true){
        auto_shoot.restartTimer();
        auto_shoot.cancel();
        auto_shoot_scheduled = false;
      }
      SmartDashboard.putBoolean("Auto Shoot", false);
    }
    if (Constants.driver2.getAButton()) {
      if (!shoot_off) {
        shoot_on = !shoot_on;
        shoot_off = true;
      }
    }
    else {
      shoot_off = false;
    }

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
