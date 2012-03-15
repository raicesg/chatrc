package edu.spsu.rgoodwin.chatrc

import swing._
import event._
import scala.swing.BorderPanel.Position._

/**
 * Created by IntelliJ IDEA.
 * User: rgoodwin
 * Date: 10/27/11
 * Time: 9:11 PM
 * To change this template use File | Settings | File Templates.
 */

case class Auth(userName: String, password: String)

class LoginDialog extends Dialog {
  var auth: Option[Auth] = None
  val userName = new TextField
  val password = new PasswordField
  val Button1 = new Button("Login")
  val Button2 = new Button("Exit")
  var errorMessage = "Wrong username or password!"

  title = "Login"
  modal = true

  // Login UI
  val loginUI = new BorderPanel {
    layout {
      new GridPanel(4,1) {
        border = Swing.EmptyBorder(5,5,5,5)
        contents += new Label("Welcome To Simple Chat: Client")
        contents += new Label ("User Name:")
        contents += userName
        contents += Button1
        //contents += new Label("Password:")
        //contents += password
      }
    } = Center

    layout {
      new FlowPanel {
        contents += Button2
      }
    } = South

    // Content listeners and reactors
    listenTo(Button1, Button2)
    reactions += {
      // Login Button
      case ButtonClicked(Button1) =>
          if (makeLogin()) {
            auth = Some(Auth(userName.text, password.text))
            close()
          }
          else {
            Dialog.showMessage(this, errorMessage, "Login Error", Dialog.Message.Error)
          }
      // Exit Button
      case ButtonClicked(Button2) =>
        close()
        ClientApp.quit()
    }
  }

  // Dialog Content
  contents = loginUI

  // Login Logic
  def makeLogin(): Boolean = {
    if (userName.text.size == 0) {
      errorMessage = "Please Enter A User Name"
      false
    } else true
  }

  centerOnScreen()
  open()
}