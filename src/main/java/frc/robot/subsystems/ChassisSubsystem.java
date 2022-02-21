// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ChassisSubsystem extends SubsystemBase {
  /** Creates a new ChassisSubsystem. */
  private double speed, speedV;
  public ChassisSubsystem() {
    speed = 1;
    speedV = .4;
  }
  public void drive(double throttle1, double throttle_turn, boolean left, boolean right) {
		if (throttle1 >= .1) {
			if (throttle_turn >= 0.1) {
        Constants.drive((throttle1 * speed), (throttle1 * speed) - (throttle_turn * speedV));
			} else if (throttle_turn <= -0.1) {
        Constants.drive((throttle1 * speed) - (-throttle_turn * speedV), (throttle1 * speed));
			} else {
        Constants.drive( throttle1 * speed , throttle1 * speed);
			}
		} else if (throttle1 <= -.1) {
			if (throttle_turn >= 0.1) {
        Constants.drive( (throttle1 * speed), (throttle1 * speed) - (-throttle_turn * speedV));
			} else if (throttle_turn <= -0.1) {
        Constants.drive( (throttle1 * speed) - (throttle_turn * speedV), (throttle1 * speed));
			} else {
        Constants.drive( throttle1 * speed, throttle1 * speed);
			}
		} 
    else if(left){
      Constants.drive(-.8,.8);
    }else if(right){
      Constants.drive(.8,-.8);
    }else {
        Constants.drive( 0, 0 );
		}

	} 
 
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
