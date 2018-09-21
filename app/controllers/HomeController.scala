package controllers


import scala.util.{Failure, Success, Try}
import javax.inject._
import models.Backend
import models._
import models.Entities._
import play.api._
import play.api.mvc._
import sangria.ast._
import sangria.execution.ExceptionHandler
import sangria.renderer._

import models.Entities._

import scala.concurrent.ExecutionContext

@Singleton
class HomeController @Inject()(implicit ec: ExecutionContext, backend: Backend, cc: ControllerComponents)
  extends AbstractController(cc) {

  // example from here https://github.com/nemoo/play-slick3-example/blob/master/app/controllers/Application.scala
  def index() = Action { _ =>
    Ok(views.html.index())
  }

//  def interval(chr: String, position: Long) = {
//    Action.async { implicit request: Request[AnyContent] =>
//      backend.findAt(DNAPosition(chr, position)).map {
//        case Success(f) => Ok(views.html.interval(f))
//        case Failure(ex) => InternalServerError(ex.toString)
//      }
//    }
//  }
//
//  def summary(chr: String, position: Long) = {
//    Action.async { implicit request: Request[AnyContent] =>
//      backend.summaryAt(DNAPosition(chr, position)).map {
//        case Success(f) => Ok(views.html.summary(f))
//        case Failure(ex) => InternalServerError(ex.toString)
//      }
//    }
//  }

  def healthcheck() = Action { request =>
    Ok("alive!")
  }
}
