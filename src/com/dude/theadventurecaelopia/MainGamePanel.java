package com.dude.theadventurecaelopia;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

	private MainThread thread;
	private Player player;

	public MainGamePanel(Context context) {
		super(context);
		DisplayMetrics displaymetrics = new DisplayMetrics();
		((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		getHolder().addCallback(this);
		player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.player_temp), width/2, height/2);
		thread = new MainThread(getHolder(), this);
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// delegating event handling to the droid
			player.handleActionDown((int)event.getX(), (int)event.getY());
			
			// check if in the lower part of the screen we exit
			if (event.getY() > getHeight() - 50) {
				thread.setRunning(false);
				((Activity)getContext()).finish();
			} else {
			}
		} if (event.getAction() == MotionEvent.ACTION_MOVE) {
			// the gestures
			if (player.isTouched()) {
				// the droid was picked up and is being dragged
				player.setX((int)event.getX());
				player.setY((int)event.getY());
			}
		} if (event.getAction() == MotionEvent.ACTION_UP) {
			// touch was released
			if (player.isTouched()) {
				player.setTouched(false);
			}
		}
		return true;
	}

	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		player.draw(canvas);
	}

	public void update() {
		// check collision with right wall if heading right
		if (player.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
				&& player.getX() + player.getBitmap().getWidth() / 2 >= getWidth()) {
			player.getSpeed().toggleXDirection();
		}
		// check collision with left wall if heading left
		if (player.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
				&& player.getX() - player.getBitmap().getWidth() / 2 <= 0) {
			player.getSpeed().toggleXDirection();
		}
		// check collision with bottom wall if heading down
		if (player.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
				&& player.getY() + player.getBitmap().getHeight() / 2 >= getHeight()) {
			player.getSpeed().toggleYDirection();
		}
		// check collision with top wall if heading up
		if (player.getSpeed().getyDirection() == Speed.DIRECTION_UP
				&& player.getY() - player.getBitmap().getHeight() / 2 <= 0) {
			player.getSpeed().toggleYDirection();
		}
		// Update the lone droid
		player.update();
	}

}
