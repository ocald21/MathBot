package dev.butter.mathbot.panebuilders

import java.awt.BorderLayout
import java.awt.Rectangle
import javax.swing.*

class ErrorPane(message: String) : JOptionPane(message, ERROR_MESSAGE) {
    init {
        setIcon(null)
        createDialog(JFrame(), "MathBot Error").isVisible = true
    }
}

class StatusPane(title: String, message: String, onExit: () -> Unit) : JFrame(title) {
    init {
        val status = JLabel(message)
            .apply {
                icon = null
                isResizable = false

                horizontalAlignment = SwingConstants.CENTER
            }

        val closeButton = JButton("Close Bot")
            .apply {
                addActionListener {
                    status.text = "MathBot shutting down..."
                    Timer(1000) {
                        onExit()
                        dispose()
                    }.start()
                }

                isResizable = false
                bounds = Rectangle(50, 20)
                verticalAlignment = SwingConstants.BOTTOM
            }

        add(status, BorderLayout.CENTER)
        add(JPanel().apply { add(closeButton) }, BorderLayout.SOUTH)

        iconImage = null
        isVisible = true
        isResizable = false
        bounds = Rectangle(300, 300)
    }
}