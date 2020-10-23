package cs3500.animator.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cs3500.animator.controller.EdiFuncs;

public class EdiHelp implements KeyListener, ActionListener {
  private EdiFuncs cont;

  public EdiHelp(EdiFuncs cont) {
    this.cont = cont;
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // do nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
      cont.togglePause();
    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      cont.restart();
    } else if (e.getKeyCode() == KeyEvent.VK_L) {
      cont.toggleLoop();
    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
      cont.speedUp();
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      cont.speedDown();
    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      cont.start();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // do nothing
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // do nothing
  }
}
