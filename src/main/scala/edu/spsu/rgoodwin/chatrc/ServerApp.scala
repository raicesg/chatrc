package edu.spsu.rgoodwin.chatrc

/**
 * Created by IntelliJ IDEA.
 * User: rgoodwin
 * Date: 10/25/11
 * Time: 6:24 PM
 * To change this template use File | Settings | File Templates.
 */

import sample.chat._
import akka.actor.Actor._

import swing._
import event._

object ServerApp extends SimpleSwingApplication {
  // GUI Layout
  val ui = new BorderPanel {
    // GUI Global Values
    var userName = "List of Users Not Yet Functioning"
    var chatService = actorOf[ChatService]

    // North Layout Global Content

    // West Layout Global Content
    val StartButton = new Button("Start Server")
    val StopButton = new Button("Stop Server")

    // Center Layout Global Content
    object chatStatus extends Label("               Chat Server Off               ")
    object userS extends Label("None")

    // GUI Layout
    import BorderPanel.Position._
    layout {
      new FlowPanel {
        contents += new Label("Welcome To Simple Chat Server")
      }
    } = North
    layout {
      new GridPanel(2,1) {
        contents += StartButton
        contents += StopButton
      }
    } = West
    layout {
      new GridPanel(4,1) {
        contents += chatStatus
        contents += new Label("Logged-In Users")
        contents += userS
        contents += Button("Exit"){quit()}
        border = Swing.EmptyBorder(30, 30, 30, 30)
      }
    } = Center

    // Content listeners and reactors
    listenTo(StartButton, StopButton)
    reactions += {
      // Start button reactions
      case ButtonClicked(StartButton) =>
        // Start Chat Server
        chatService.start()
        // Display Logged In Users
          userS.text = userName
        // Chat Server Status
        chatStatus.text = "Chat Server Is Now Operating"

      // Stop button reactions
      case ButtonClicked(StopButton) =>
        // Start Chat Server
        chatService.stop()
        // Chat Server Status
        chatStatus.text = "Chat Server Has Been Disabled"
    }
  }

  // MainFrame
  def top = new MainFrame {
    title = "Simple Chat Server: Server"
    contents = ui
  }
}