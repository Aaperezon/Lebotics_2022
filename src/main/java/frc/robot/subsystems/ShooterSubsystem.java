// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
  private double interval;
  private double MAX_speed_motor, MIN_speed_motor, MAX_rpm, MIN_rpm;
  private boolean modify_up, modify_down;
  private double target;
  long start_time, end_time, current_time, target_time;

  public ShooterSubsystem() {
    interval = .1;
    MAX_speed_motor = 1.0;
    MIN_speed_motor = -1.0;
    MAX_rpm = 1000;
    MIN_rpm = 0;
    modify_up = true;
    modify_down = true;
    start_time = System.currentTimeMillis();


  }
 
  public void setTarget(double target_){
    target = target_;
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
    return Constants.shooter_encoder.getRate()*60/120;
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
  //   Constants.shooter_motor.set(speed);
  // }
  public void startEngine(double speed){
    Constants.shooter_motor.set(speed);
    // System.out.println(test);
  }
  
  public void stop(){
    Constants.shooter_motor.set(0);
  }

  public void shoot(boolean shoot){
    if(shoot){
      Constants.left_servo_shooter.setAngle(0);
      Constants.right_servo_shooter.setAngle(180);
      start_time = System.currentTimeMillis();
    }else{
      // end_time = System.currentTimeMillis();
      // current_time = end_time - start_time;
      // if(current_time < 1000){
      //   Constants.servo_shooter.setAngle(0);
      // }else{
      //   Constants.servo_shooter.setAngle(92);
      // }
      Constants.left_servo_shooter.setAngle(90);
      Constants.right_servo_shooter.setAngle(90);

    }
   
  }
  @Override
  public void periodic() {

  }

  
}
