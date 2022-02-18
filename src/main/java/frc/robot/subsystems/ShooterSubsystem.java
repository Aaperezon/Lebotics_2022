// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private double speed, interval;
  private double MAX, MIN;
  private boolean modify_up, modify_down;
  private int P,I,D;
  private int integral, previous_error, setpoint;

  public ShooterSubsystem() {
    speed = 0;
    interval = .1;
    MAX = 1.0;
    MIN = -1.0;
    modify_up = true;
    modify_down = true;
    setpoint =0 ;
  }
  public void canModify(){
    modify_up = true;
    modify_down = true;
  }
  public void speedUp(){
    if(speed <= MAX && modify_up == true){
      speed+=interval;
      modify_up = false;
    }
  }
  public void speedDown(){
    if(speed >= MIN && modify_down == true){
      speed-=interval;
      modify_down = false;
    }
  }


  public void shoot(){
    Constants.shooter_motor.set(speed);
  }
  public void stop(){
    Constants.shooter_motor.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //System.out.println("Speed:"+speed+" asd:"+Constants.shooter_encoder.getRate()+ "RPM:"+Constants.shooter_encoder.getRate()*60/120);
    SmartDashboard.putNumber("Shooter Speed", speed*10);
    SmartDashboard.putNumber("RPM", Constants.shooter_encoder.getRate()*60/120);

  }

  
}
