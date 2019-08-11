package controllers

import javax.inject.{Inject, Singleton}
import models.User
import play.api.libs.json.{JsPath, JsValue, Json, Writes}
import play.api.mvc.{
  AbstractController,
  Action,
  AnyContent,
  ControllerComponents
}
import play.api.libs.functional.syntax._

@Singleton
class UserController @Inject()(
    cc: ControllerComponents
) extends AbstractController(cc) {
  def me: Action[AnyContent] = Action { implicit request =>
    val users = Seq(
      User(Some("vietnam"),
           Some("Trang Mai"),
           Some("trangmaiq@gmail.com"),
           Some("b699722a16ce43c58369dd72085c4fae"),
           Some("trangmaiq"))
    )
    Ok(UserJson.toJson(Some(users)))
  }
}

object UserJson {
  implicit val userWrites: Writes[User] = (
    (JsPath \ "country").write[Option[String]] and
      (JsPath \ "display_name").write[Option[String]] and
      (JsPath \ "email").write[Option[String]] and
      (JsPath \ "id").write[Option[String]] and
      (JsPath \ "username").write[Option[String]]
  )(unlift(User.unapply))

  def toJson(users: Option[Seq[User]]): JsValue = Json.toJson(users)
}
