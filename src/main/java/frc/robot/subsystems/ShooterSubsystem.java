// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  
  private static Spark shooter_motor = new Spark(9);
  private static Encoder shooter_encoder = new Encoder(8,9);
  private static Servo left_servo_shooter = new Servo(0);
  private static final Servo right_servo_shooter = new Servo(1);


  private double interval;
  private double MAX_speed_motor, MIN_speed_motor, MAX_rpm, MIN_rpm;
  private boolean modify_up, modify_down;
  private double target;
  long start_time, end_time, current_time, target_time;

  public ShooterSubsystem() {
    interval = .1;
    MAX_speed_motor = 1.0;
    MIN_speed_motor = -1.0;
    MAX_rpm = 5000;
    MIN_rpm = 0;
    modify_up = true;
    modify_down = true;
    start_time = System.currentTimeMillis();
    shooter_encoder.setDistancePerPulse(Math.PI*15.24/5); //distance per pulse is pi* (wheel diameter / counts per revolution)


  }
 
  public void setTarget(double target_){
    target = target_;
  }
  public void resetEncoder(){
    shooter_encoder.reset();
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
    return shooter_encoder.getRate()*60/120;
  }
 

  public void canModify(){
    modify_up = true;
    modify_down = true;
  }
  // public void speedUp(){
  //   if(speed < MAX_speed_motor && modify_up == true){
  //     speed+=interval;
  //     modify_up = false;
  //   }
  // }
  // public void speedDown(){
  //   if(speed > MIN_speed_motor && modify_down == true){
  //     speed-=interval;
  //     modify_down = false;
  //   }
  // }
  // public void setShooterSpeed(double speed_){
  //   if(speed < MAX_speed_motor && speed > MIN_speed_motor){
  //     speed = speed_;
  //   }
  // }
  // public void startEngine(){
  //   shooter_motor.set(speed);
  // }
  public void startEngine(double speed){
    shooter_motor.set(speed);
    // System.out.println(test);
  }
  
  public void stop(){
    shooter_motor.set(0);
  }

  public void shoot(boolean shoot){
    if(shoot){
      left_servo_shooter.setAngle(0);
      right_servo_shooter.setAngle(180);
      start_time = System.currentTimeMillis();
    }else{
      // end_time = System.currentTimeMillis();
      // current_time = end_time - start_time;
      // if(current_time < 1000){
      //   servo_shooter.setAngle(0);
      // }else{
      //   servo_shooter.setAngle(92);
      // }
      left_servo_shooter.setAngle(90);
      right_servo_shooter.setAngle(90);

    }
   
  }
  @Override
  public void periodic() {

  }

  
}
