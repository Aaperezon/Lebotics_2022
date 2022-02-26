// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private double speed, interval;
  private double MAX_speed_motor, MIN_speed_motor, MAX_rpm, MIN_rpm;
  private boolean modify_up, modify_down;
  private double target;
  public ShooterSubsystem() {
    speed = 0;
    interval = .1;
    MAX_speed_motor = 1.0;
    MIN_speed_motor = -1.0;
    MAX_rpm = 1000;
    MIN_rpm = 0;
    modify_up = true;
    modify_down = true;
    Constants.shooter_pid.reset();
  }
  public void setkP(double kp){
    Constants.shooter_pid.setP(kp);
  }
  public void setkI(double ki){
    Constants.shooter_pid.setI(ki);
  }
  public void setkD(double kd){
    Constants.shooter_pid.setD(kd);
  }
  public void setTarget(double target_){
    target = target_;
  }

  
  public double getTarget(){
    return target;
  }
  public void setMoreTarget(){
    if(target < MAX_rpm && modify_up == true){
      target += 100;
      modify_up = false;
    }
  }
  public void setLessTarget(){
    if(target > MIN_rpm && modify_down == true){
      target -= 100;
      modify_down = false;
    }
  }
  public double getRPM(){
    return Constants.shooter_encoder.getRate()*60/120;
  }
 

  public void canModify(){
    modify_up = true;
    modify_down = true;
  }
  public void speedUp(){
    if(speed < MAX_speed_motor && modify_up == true){
      speed+=interval;
      modify_up = false;
    }
  }
  public void speedDown(){
    if(speed > MIN_speed_motor && modify_down == true){
      speed-=interval;
      modify_down = false;
    }
  }
  public void setShooterSpeed(double speed_){
    if(speed < MAX_speed_motor && speed > MIN_speed_motor){
      speed = speed_;
    }
  }
  public void shoot(boolean shoot){
    if(shoot){
      Constants.left_servo.setAngle(0);
      Constants.right_servo.setAngle(180);
    }else{
      Constants.left_servo.setAngle(90);
      Constants.right_servo.setAngle(90);
    }
  }
  public void startEngine(){
    Constants.shooter_motor.set(speed);
  }
  public void startEngine(double speed){
    // double test = Constants.shooter_pid.calculate(getRPM(), getTarget());
    Constants.shooter_motor.set(speed);
    // System.out.println(test);
  }
  
  public void stop(){
    Constants.shooter_motor.set(0);
    Constants.shooter_pid.reset();
  }

 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  
}
