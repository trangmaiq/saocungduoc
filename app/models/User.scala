package models

case class User(
    country: Option[String],
    displayName: Option[String],
    email: Option[String],
    id: Option[String],
    username: Option[String]
) {}
