// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.revrobotics.AlternateEncoderType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  
  // private static Spark shooter_motor = new Spark(9);
  // private static Encoder shooter_encoder = new Encoder(8,9);
  private static final CANSparkMax shooter_motor = new CANSparkMax(6, MotorType.kBrushless);
  private static final Servo shooter_servo = new Servo(0);
  private static final VictorSP shooter_rail = new VictorSP(7);
  private static final Servo shooter_camera = new Servo(9);
  private double interval;
  private double MAX_speed_motor, MIN_speed_motor, MAX_rpm, MIN_rpm;
  private boolean modify_up, modify_down;
  private double target;
  long start_time, end_time, current_time, target_time;
  private double speed;
  public ShooterSubsystem() {
    shooter_motor.restoreFactoryDefaults();
    speed = 0.5;
    interval = .1;
    MAX_speed_motor = 1.0;
    MIN_speed_motor = 0;
    MAX_rpm = 5000;
    MIN_rpm = 0;
    modify_up = true;
    modify_down = true;
    start_time = System.currentTimeMillis();
    // shooter_encoder.setDistancePerPulse(Math.PI*15.24/5); //distance per pulse is pi* (wheel diameter / counts per revolution)


  }
  public void setAngle(double deg){
    shooter_camera.setAngle(deg);
  }
 
  public void setTarget(double target_){
    target = target_;
  }
  public void resetEncoder(){
    // shooter_encoder.reset();
  }

  public void setTargetRPM(double new_target){
    target = new_target;
  }
  public double getTargetRPM(){
    return target;
  }
  public void setMoreTargetRPM(){
    if(target < MAX_rpm && modify_up == true){
      target += 100;
      modify_up = false;
    }
  }
  public void setLessTargetRPM(){
    if(target > MIN_rpm && modify_down == true){
      target -= 100;
      modify_down = false;
    }
  }
  public double getRPM(){
    return shooter_motor.getEncoder().getVelocity();
    // return shooter_encoder.getRate()*60/120;
  }
  public void setCamera(){
    shooter_camera.setAngle(90);
  }
  
 
  public double getSpeed(){
    return speed;
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
  public void startEngine(){
    shooter_motor.set(speed);
  }
  public void startEngine(double speed){
    shooter_motor.set(speed);
  }
  
  public void stop(){
    shooter_motor.set(0);
  }

  public void shoot(boolean shoot, boolean reverse){
    if(shoot){
      shooter_servo.setAngle(0);
      shooter_rail.set(-.8);
      // start_time = System.currentTimeMillis();
    }else if(reverse){
      shooter_rail.set(.8);
      shooter_servo.setAngle(0);
    }else{
      // end_time = System.currentTimeMillis();
      // current_time = end_time - start_time;
      // if(current_time < 1000){
      //   servo_shooter.setAngle(0);
      // }else{
      //   servo_shooter.setAngle(92);
      // }
      shooter_rail.set(0);
      shooter_servo.setAngle(90);
    }
   
  }
 
  @Override
  public void periodic() {

  }

  
}
