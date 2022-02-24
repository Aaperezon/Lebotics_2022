// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  /** Creates a new ClimberSubsystem. */
  public ClimberSubsystem() {}


  public void move(double speed){
    if(speed >= .1 || speed <= -.1){
      Constants.climber_left.set(speed);
      Constants.climber_right.set(speed);
    }else{
      stop();
    }
  }
  public void moveLeft(double speed){
    if(speed >= .1 || speed <= -.1){
      Constants.climber_left.set(speed);
    }
    else{
      Constants.climber_left.set(0);

    }
  }
  public void moveRight(double speed){
    if(speed >= .1 || speed <= -.1){
      Constants.climber_right.set(speed);
    }
    else{
      Constants.climber_right.set(0);
    }
  }

  public void stop(){
    Constants.climber_left.set(0);
    Constants.climber_right.set(0);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
