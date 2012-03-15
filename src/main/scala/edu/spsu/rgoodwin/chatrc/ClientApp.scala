package edu.spsu.rgoodwin.chatrc

import sample.chat._
import akka.actor.Actor._
import java.awt.Color
import swing._
import event._
import scala.swing.BorderPanel.Position._
import  scala.collection.mutable.Map
import swing.ListView._

/**
 * Created by IntelliJ IDEA.
 * User: rgoodwin
 * Date: 10/26/11
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */


object ClientApp extends SimpleSwingApplication {
  val auth = new LoginDialog().auth.getOrElse(throw new IllegalStateException("You should login!!!"))
  val ui = new BorderPanel {
    // GUI Global Values
    var dialogMap = Map[Int, String]()
    var mapCount = 1

    // North Layout Global Content

    // Center Layout Global Content
    var tempDialog = List[String]()
    var chatDialog = new ListView(tempDialog) {
          border = Swing.LineBorder(Color.LIGHT_GRAY, 10)
          fixedCellWidth = 15
          fixedCellHeight = 15
        }

    // South Layout Global Content
    object chatInput extends TextField
    val SendButton = new Button("Send")

    // GUI Layout
    import BorderPanel.Position._
    layout {
      new FlowPanel {
        contents += new Label("Welcome: ")
        contents += new Label(auth.userName.toString)
      }
    } = North
    layout {
      new GridPanel(1,1) {
        contents += chatDialog
      }
    } = Center
    layout {
      new GridPanel(4,4) {
        contents += chatInput
        contents += SendButton
        contents += new FlowPanel(FlowPanel.Alignment.Right)(Button("Refresh"){
          chatDialog.listData = myClient.chatLog.log.toList
        })
        contents += new FlowPanel(FlowPanel.Alignment.Right)(Button("Exit"){quit()})
      }
    } = South

    // Login to Simple Chat: Server
    val myClient = new ChatClient(auth.userName.toString)
    myClient.login

    // Content listeners and reactors
    listenTo(SendButton)
    reactions += {
      case ButtonClicked(SendButton) =>
        if (chatInput.text.size != 0) {
          myClient.post(chatInput.text)
          chatDialog.listData = myClient.chatLog.log.toList
          chatInput.text = ""
          mapCount += 1
        }
    }

  }

  def top = new MainFrame {
    title = "Simple Chat: Client"
    contents = ui
    size = Swing.pair2Dimension(300, 500)
    centerOnScreen()
  }

}






