package controllers

import models.{Robot, RobotState}
import play.api.libs.json.Json
import play.api.mvc._

import javax.inject._

/**
 * This controller to handle robot inventory, robot related APIs
 */
//@Singleton // why
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  implicit val format2 = Json.format[RobotState] // why
  implicit val format = Json.format[Robot] // why

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def getAll() = Action {
    val roboState = new RobotState(1, "AWAITED", "In production")
    val robots = List(new Robot(1, "Robo1", "2022", 15.6F, "Red", roboState),
      new Robot(2, "Robo2", "2023", 20F, "Black", roboState))
    Ok(Json.toJson(robots))
  }

  def getById(id: Long) = Action {
    val roboState = new RobotState(1, "AWAITED", "In production")
    val robot = new Robot(1, "Robo1", "2022", 15.6F, "Red", roboState)
    Ok(Json.toJson(robot))
  }
}