// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ChassisSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AimShootCommand extends CommandBase {
  ChassisSubsystem chassis_subsystem;
  ShooterSubsystem shooter_subsystem;
  private PIDController shoot_pid;
  private PIDController aim_pid;

  boolean terminate;
  double pipeline;
  public AimShootCommand(ChassisSubsystem chassis_subsystem, ShooterSubsystem shooter_subsystem, double pipeline) {

    this.chassis_subsystem = chassis_subsystem;
    addRequirements(chassis_subsystem);
    terminate = false;
    aim_pid = new PIDController(.017, 0, 0);

    shoot_pid = new PIDController(.025, 0, 0);
    this.shooter_subsystem = shooter_subsystem;
    addRequirements(shooter_subsystem);
    this.pipeline = pipeline;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setDouble(pipeline);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pipeline = NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").getDouble(0);
    if(pipeline == 0){
      reflectivePipeline();
    }else if(pipeline == 1){
      redBallPipeline();
    }else if(pipeline == 2){

    }
   

  }
  private void reflectivePipeline(){
     //AIM WITH CHASSIS
     double x_target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
     double chassis_speed = aim_pid.calculate(x_target, 0);
     // System.out.println(speed);
     chassis_subsystem.drive(-chassis_speed, chassis_speed);
 
    //  //SHOOT
    //  double y_target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    //  double shooter_speed = shoot_pid.calculate(y_target, 0);
    //  shooter_subsystem.startEngine(shooter_speed);
    //  SmartDashboard.putNumber("RPM", shooter_subsystem.getRPM());
  }
  private void redBallPipeline(){
    //AIM WITH CHASSIS
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    double x_target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double chassis_speed = aim_pid.calculate(x_target, 0);
    // System.out.println(speed);
    chassis_subsystem.drive(-chassis_speed, chassis_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return terminate;
  }
}