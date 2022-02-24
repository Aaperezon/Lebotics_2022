// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class AimSubsystem extends SubsystemBase {

  public AimSubsystem() {
  }

  public void move(double speed1, double speed2){
    if( (speed1 >= .1 || speed1 <= -.1) && (speed2 >= .1 || speed2 <= -.1)){
      Constants.drive(speed1, speed2);
    }else{
      stop();
    }
  }
  public void stop(){
    Constants.drive(0, 0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
