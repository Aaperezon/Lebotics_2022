// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ChassisSubsystem extends SubsystemBase {

  
  private static VictorSPX chassis_motor_left1 = new VictorSPX(1);
  private static VictorSPX chassis_motor_left2 = new VictorSPX(2);
  private static VictorSPX chassis_motor_right1 = new VictorSPX(0);
  private static VictorSPX chassis_motor_right2 = new VictorSPX(3);
  private static Encoder left_encoder = new Encoder(0,1);
  private static Encoder right_encoder = new Encoder(2,3);

  private double speed, speedV;
  public ChassisSubsystem() {
    speed = 1;
    speedV = .4;
    left_encoder.setDistancePerPulse(Math.PI*15.24/360); //distance per pulse is pi* (wheel diameter / counts per revolution)
    right_encoder.setDistancePerPulse(Math.PI*15.24/360); //distance per pulse is pi* (wheel diameter / counts per revolution)
  }
  public void resetEncoders(){
    left_encoder.reset();
    right_encoder.reset();
  }
  public double getLeftDistance(){
    return left_encoder.getDistance();
  }
  public double getRightDistance(){
    return right_encoder.getDistance();
  }
  public void drive(double left_speed, double right_speed){
    if( (left_speed >= .1 || left_speed <= -.1) && (right_speed >= .1 || right_speed <= -.1)){
      chassis_motor_left1.set(ControlMode.PercentOutput ,left_speed);
      chassis_motor_left2.set(ControlMode.PercentOutput, left_speed);
      chassis_motor_right1.set(ControlMode.PercentOutput ,-right_speed);
      chassis_motor_right2.set(ControlMode.PercentOutput ,-right_speed);
    }else{
      chassis_motor_left1.set(ControlMode.PercentOutput ,0);
      chassis_motor_left2.set(ControlMode.PercentOutput, 0);
      chassis_motor_right1.set(ControlMode.PercentOutput ,0);
      chassis_motor_right2.set(ControlMode.PercentOutput ,0);
    }
  }
  public void drive(double throttle1, double throttle_turn, boolean left, boolean right) {
		if (throttle1 >= .1) {
			if (throttle_turn >= 0.1) {
        drive((throttle1 * speed), (throttle1 * speed) - (throttle_turn * speedV));
			} else if (throttle_turn <= -0.1) {
        drive((throttle1 * speed) - (-throttle_turn * speedV), (throttle1 * speed));
			} else {
        drive( throttle1 * speed , throttle1 * speed);
			}
		} else if (throttle1 <= -.1) {
			if (throttle_turn >= 0.1) {
        drive( (throttle1 * speed), (throttle1 * speed) - (-throttle_turn * speedV));
			} else if (throttle_turn <= -0.1) {
        drive( (throttle1 * speed) - (throttle_turn * speedV), (throttle1 * speed));
			} else {
        drive( throttle1 * speed, throttle1 * speed);
			}
		} 
    else if(left){
      drive(-.6,.6);
    }else if(right){
      drive(.6,-.6);
    }else {
        drive( 0, 0 );
		}

	} 
  
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
