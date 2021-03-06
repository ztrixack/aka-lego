package controllers

import bases.BasedSpec
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import play.api.libs.json.Json
import play.api.test.FakeRequest
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class FruitingControllerSpec
  extends BasedSpec {

  "POST /api/v1/fruiting/stamp" should {
    "return stamped JSON" in {
      val thermal = 42.2

      val result = route(app, FakeRequest(POST, CREATE_URL)
        .withJsonBody(Json.parse(
          s"""
             |{
             | "sensor_id" : "atdkjfadfadfs",
             | "thermal" : $thermal,
             | "humidity": 31.5,
             | "co2": 11.2,
             | "uv": 12.2
             |}
             | """.stripMargin)
        ))
        .get

      status(result) mustBe OK
      contentType(result) mustBe Some("application/json")
      contentAsString(result) must include(thermal.toString)
    }
  }

  private lazy val CREATE_URL = "/api/v1/fruiting/stamp"

}
