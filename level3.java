package com.games.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.Button;

import box2dLight.RayHandler;

/**
 * Created by D Sai Vamshi on 04-04-2018.
 */

public class level3 implements Screen {

    private OrthographicCamera camera;

    private Box2DDebugRenderer debug;
    private World world;
    private Body player, platform , Obstruc ,Obstruc1,Obstrcu2,Obstruc3 ,Obstruct4,t,t1,t3,t4,trapup,key , key2;
    private RayHandler rayH;
    // private PointLight pointLi;
    private static final float PPM = 32;
    private Button button1, button2;
    private BodyDef bodyDef = new BodyDef();
    private FixtureDef fixtureDef = new FixtureDef();
    private float horizontalForce = 0;
    private float speed = 1.0f;
    private boolean check = false , check2 = true , checkk=true , checkk2 = true;
    public float r=0,g=0,b=0,a=1 ,radius = 2.1f , mass = 10.0f;
    //public Fixture fix ,fix1 , nonfix;
    private Body body;
    //private Music music;
    private Screen scr;
    private SpriteBatch batch,batch1;    // not using for now see above comment
    private Texture tex , tex1 , tex2;   // not using for now see above comment
    private ShapeRenderer shaperender;

    private boolean changelevel = false , isCheck = true;
    private ContactListener lisener;   // not using for now
    private String id;




    @Override
    public void show() {



        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();


        camera = new OrthographicCamera();
        camera.setToOrtho(false, w / 2, h / 3);


        world = new World(new Vector2(0, -9.8f), false);





        world.setContactListener(new ContactListener() {


                                     @Override
                                     public void beginContact(Contact contact) {


                                         Fixture fix  = contact.getFixtureA();
                                         Fixture fix1 = contact.getFixtureB();



                                         if(contact.getFixtureA().getBody() ==player && contact.getFixtureB().getBody() == Obstruc
                                                 ||contact.getFixtureA().getBody() ==Obstruc && contact.getFixtureB().getBody() == player){

                                             System.out.print("collision");
                                             ((Game)Gdx.app.getApplicationListener()).setScreen(new level3());


                                         }

                                         if(contact.getFixtureA().getBody() ==player && contact.getFixtureB().getBody() == trapup
                                                 ||contact.getFixtureA().getBody() ==trapup && contact.getFixtureB().getBody() == player){

                                             System.out.print("collision");
                                             ((Game)Gdx.app.getApplicationListener()).setScreen(new level3());


                                         }
                                         if(fix1.getUserData()==null||fix.getUserData()==null){


                                             System.out.println("Collision happend");



                                         }

                                         if(contact.getFixtureA().getBody() ==key && contact.getFixtureB().getBody() == key2
                                                 ||contact.getFixtureA().getBody() ==key2 && contact.getFixtureB().getBody() == key){

                                             System.out.print("collision");
                                             ((Game)Gdx.app.getApplicationListener()).setScreen(new Tutlevel());


                                         }

//                                         if(isTutContact(fix ,fix1)) {
//
//                                             CreateBox cba =(CreateBox)fix.getUserData();
//                                             CreateBox cbb = (CreateBox)fix1.getUserData();
//
//                                             cba.test();
//
//
//                                         }
//
                                             return;


                                     }

                                     @Override
                                     public void endContact(Contact contact) {

//
//                                         Fixture fix  = contact.getFixtureA();
//                                         Fixture fix1 = contact.getFixtureB();
//
//
//
//                                         if(fix1==null || fix==null) return;
//                                         if(fix1.getUserData()==null||fix.getUserData()==null) return;

                                     }

                                     @Override
                                     public void preSolve(Contact contact, Manifold oldManifold) {

                                     }

                                     @Override
                                     public void postSolve(Contact contact, ContactImpulse impulse) {

                                     }




        }



        );
        debug = new Box2DDebugRenderer();
        rayH = new RayHandler(world);

        rayH.setBlur(true);

        rayH.setBlurNum(3);
        rayH.setShadows(true);


        player = createplayer(60, 40, 10.0f, 20.0f, false); // player

       // destoryobj(80,-40 ,110.0f,80.0f, true);
        //destoryobj(180,-20,90.0f,120.0f,true);
        //destoryobj(360,110,200.0f,100.0f,true);
        trapup =destoryobj(30,155,4080.0f,5.0f,true);   // traps top
        Obstruc =destoryobj(0,-76,4080.0f,5.0f,true);   // traps2 bottom


        //Obstrcu2 = destoryobj(360,-30,80.0f,5.0f,true);   //floating platfrom




        //=======================floating platfroms----------------------------------------//

        Obstrcu2 = destoryobj(160,-30,50.0f,50.0f,true);   //floating platfrom
        Obstruc3 = destoryobj(560,-30,200.0f,50.0f,true);   //floating platfrom
        Obstruct4 = destoryobj(310,40,150.0f,210.0f,true);   //floating platfrom
        t1 = destoryobj(310,-5,50,50,true);
        t3 = destoryobj(700,-5,80,150,true);
        t4 = destoryobj(720,-45,40,75,true);

       //----------------------------------------------------------------------------------//
        key = destoryobj(740,10,10,10,true);




        createplayer(750,40,20.0f,240.0f,true); // end of the level 1
        createplayer(-10,40,20.0f,241.0f,true); //start of the level 1


        //destoryobj(90,125,20.0f,70.0f,true,true);
        //ground
        platform = createEdge(0,-80,50,0,true);
        //Roof
        createEdge(0,160,50,0,true);
                                                         //createplayer(180,50,30,30,false,"crate");//create box
        // createplayer(740, 10, 15, 15, true,fix); //contact box






        //Texbody =  createplayer(0,-265,600,50,true);  // for texture
        //--------------not using for now-------------//
        batch = new SpriteBatch();


        tex = new Texture("bricks.png");
        tex1 = new Texture("create box.png");

        //tex1 = new Texture("blue.png");
        //tex2 = new Texture("yellow.png");
        //----------------------------------------------//




        shaperender = new ShapeRenderer();




    }







    @Override
    public void render(float delta) {

        // Sprite batch in declared here where render updates every frame
        // in box2d world , we cannot use Sprite batch out side this

        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.1f);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //setScreen(new Splash());

        Colorchange(r,g,b,a);
        debug.render(world, camera.combined.cpy().scl(PPM)); // ray handler should be below this to shadows and lights
        rayH.render();
        //rayH.setCombinedMatrix(camera);
        //rayH.updateAndRender();
        //world.step(1/60f , 6 ,2);
        rayH.updateAndRender();



        if(check == false) {

            shaperender.begin(ShapeRenderer.ShapeType.Filled);

            //Obstruc =  destoryobj(70 , 70 ,20.0f,180.0f, true, shaperender);

            shaperender.setColor(Color.CYAN);
            shaperender.rect( 235,-65,150.0f,210.0f);

            shaperender.end();

        } else if (check == true) {


            shaperender.begin(ShapeRenderer.ShapeType.Line);


            //.rect(320, -32, 80.0f, 5.0f);
            shaperender.setColor(Color.CYAN);
            shaperender.rect( 235,-65,150.0f,210.0f);
            shaperender.end();


        }
        if(check2 == true) {
            shaperender.begin(ShapeRenderer.ShapeType.Filled);

            shaperender.setColor(Color.RED);                                     // this works that way  it should work like overlapping things
            shaperender.rect(-400, -78.0f, 2090.0f, 5);
            shaperender.rect(-400, 153.0f, 2090.0f, 6);
            shaperender.end();

        } else if(check2 == false){

            shaperender.begin(ShapeRenderer.ShapeType.Line);

            shaperender.setColor(Color.RED);                                     // this works that way  it should work like overlapping things
            shaperender.rect(-400, -78.0f, 2090.0f, 5);
            shaperender.rect(-400, 153.0f, 2090.0f, 6);
            shaperender.end();





        }

        if(checkk == true) {
            shaperender.begin(ShapeRenderer.ShapeType.Filled);

            shaperender.setColor(Color.YELLOW);                                     // this works that way  it should work like overlapping things
            shaperender.rect(460,-55,  200.0f,50.0f);
            shaperender.end();

        } else if(checkk == false){

            shaperender.begin(ShapeRenderer.ShapeType.Line);

            shaperender.setColor(Color.YELLOW);                                     // this works that way  it should work like overlapping things
            shaperender.rect(460,-55, 200.0f,50.0f);
            shaperender.end();





        }

        if(checkk2 == true)
        {

            shaperender.begin(ShapeRenderer.ShapeType.Filled);

            shaperender.setColor(Color.VIOLET);                                     // this works that way  it should work like overlapping things
            shaperender.rect(660,-80,80,150);
            shaperender.end();

        }else if(checkk2 == false) {


            shaperender.begin(ShapeRenderer.ShapeType.Line);

            shaperender.setColor(Color.VIOLET);                                     // this works that way  it should work like overlapping things
            shaperender.rect( 660,-80,80,150);
            shaperender.end();



        }












        shaperender.begin(ShapeRenderer.ShapeType.Filled);

        shaperender.setColor(Color.BROWN);                                     // this works that way  it should work like overlapping things
        shaperender.rect( 135,-55,50.0f,50.0f);
        shaperender.rect( 286,-30,50,50);
        shaperender.rect(700,-80,40,75);
        shaperender.end();



        shaperender.begin(ShapeRenderer.ShapeType.Line);

        shaperender.setColor(Color.WHITE);
        shaperender.rect(710, -5, 30, 30);  // final objective in the level 1
        shaperender.end();

        batch.begin();
        //batch.draw(tex,Texbody.getPosition().x*PPM/2-(tex.getWidth()/2),Texbody.getPosition().y*PPM-(tex.getHeight()/2)); test case
         //for crates//




        //----------//
        //titles of the bottom platform
        batch.draw(tex,1000,-280,200,200);
        batch.draw(tex,800,-280,200,200);
        batch.draw(tex,600,-280,200,200);
        batch.draw(tex,0,-280,200,200);
        batch.draw(tex,400,-280,200,200);
        batch.draw(tex,600,-280,200,200);
        batch.draw(tex,200,-280,200,200);
        batch.draw(tex,-200,-280,200,200);
        batch.draw(tex,-400,-280,200,200);
        batch.draw(tex,-600,-280,200,200);
        batch.draw(tex,-800,-280,200,200);
        batch.draw(tex,-1000,-280,200,200);

        //titles of the top platform
        batch.draw(tex,1000,160,200,200);
        batch.draw(tex,800,160,200,200);
        batch.draw(tex,0,  160,200,200);
        batch.draw(tex,400,160,200,200);
        batch.draw(tex,600,160,200,200);
        batch.draw(tex,200,160,200,200);
        batch.draw(tex,-200,160,200,200);
        batch.draw(tex,-400,160,200,200);
        batch.draw(tex,-600,160,200,200);
        batch.draw(tex,-800,160,200,200);
        batch.draw(tex,-1000,160,200,200);
        //batch.draw(tex,30,-60,50,60);



        batch.end();







    }

    @Override
    public void resize(int width, int height) {

        camera.setToOrtho(false, width / 2, height / 2);

    }



    //-------------------------------------------never used till now ----------------------------//
    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    //--------------------------------------------------------------------------------------------//







    @Override
    public void dispose() {


        rayH.dispose();
        world.dispose();
        debug.dispose();

        batch.dispose();  //not using this for now
    }
    //----------------------------------------made up methods(defined methods)---------------------------//




    public void inputUpdate(final float delta){


        Gdx.input.setInputProcessor(new InputProcessor() {


                                        @Override
                                        public boolean keyDown(int keycode) {


                                            switch (keycode) {
                                                case Input.Keys.W:

                                                    float jump = 3.0f;

                                                    player.setLinearVelocity(player.getLinearVelocity().x , jump);
                                                    // world.destroyBody(body);






                                                    //Colorchange(0.3f, 0.4f, 0.5f, 1.0f);
                                                    //movement.y = speed;



                                                    break;

                                                case Input.Keys.A:



                                                    horizontalForce -= speed;
                                                    player.setLinearVelocity(horizontalForce, player.getLinearVelocity().y);






                                                    //Colorchange(0.5f, 0.2f, 0.7f, 1.0f);
                                                    //movement.x = -speed;

                                                    break;

                                                case Input.Keys.S:




                                                    //Colorchange(0.3f, 0.4f, 0.2f, 1.0f);  Don't unComment this
                                                    //movement.y = -speed;

                                                    break;

                                                case Input.Keys.D:


                                                    horizontalForce += speed;
                                                    player.setLinearVelocity(horizontalForce , player.getLinearVelocity().y);



                                                    //Colorchange(0.4f, 0.7f, 0.8f, 1.0f);
                                                    //movement.x = speed;

                                                    break;

                                                case Input.Keys.UP:


                                                    // Red values
                                                    r=1.0f;
                                                    g=0.0f;
                                                    b=0.0f;
                                                    //body.destroyFixture(fixture);
                                                    //Obstruc.destroyFixture(fix);
                                                   // Obstruc.setActive(false);
                                                    //Obstruc1.setActive(false);
                                                   // Obstrcu2.setActive(false);
                                                    key2 =createplayer( 310,40,30,30, false);
                                                    trapup.setActive(false);
                                                    Obstruc.setActive(false);

                                                    check2 = false;

                                                    System.out.println("is in 2");


                                                    break;


                                                case Input.Keys.LEFT:
                                                    // Yellow Values
                                                    r=1.0f;
                                                    g=1.0f;
                                                    b=0.0f;
                                                    checkk = false;

                                                    Obstruc3.setActive(false);






                                                    break;


                                                case Input.Keys.RIGHT:


                                                    //White values
                                                    r=0.0f;
                                                    g=1.0f;
                                                    b=1.0f;
                                                    check = true;
                                                    Obstruct4.setActive(false);




                                                    break;




                                                case Input.Keys.DOWN:


                                                    //Violet values
                                                    r=0.7f;
                                                    g=0.1f;
                                                    b=0.7f;
                                                    a =1.0f;
                                                    t3.setActive(false);
                                                    checkk2 = false;


                                                    break;




                                                default:



                                                    r=0.0f;
                                                    g=0.0f;
                                                    b=0.0f;
                                                    //Colorchange(0, 0, 0, 1);

                                                    break;
                                            }







                                            return true;
                                        }

                                        @Override
                                        public boolean keyUp(int keycode) {

                                            switch (keycode) {
                                                case Input.Keys.UP:

                                                    r=1.0f;
                                                    g=0.0f;
                                                    b=0.0f;

//                                                    Obstruc.setActive(true);
//                                                    Obstruc1.setActive(true);
//                                                    Obstrcu2.setActive(true);
//                                                    check = false;
                                                    trapup.setActive(true);
                                                    Obstruc.setActive(true);
                                                    check2 = true;
                                                    break;

                                                case Input.Keys.LEFT:


                                                    r=1.0f;
                                                    g=1.0f;
                                                    b=0.0f;

                                                    Obstruc3.setActive(true);
                                                    t1.setActive(true);
                                                    checkk = true;
                                                    break;

                                                case Input.Keys.RIGHT:

                                                    r=0.0f;
                                                    g=1.0f;
                                                    b=1.0f;

                                                    check = false;
                                                    Obstruct4.setActive(true);


                                                    break;

                                                case Input.Keys.DOWN:


                                                    //Violet values
                                                    r=0.7f;
                                                    g=0.1f;
                                                    b=0.7f;
                                                    a =1.0f;
                                                    t3.setActive(true);
                                                    checkk2 = true;

                                                    break;




                                                default:



                                                    r=0.0f;
                                                    g=0.0f;
                                                    b=0.0f;
                                                    //Colorchange(0, 0, 0, 1);

                                                    break;
                                            }






                                            return true;
                                        }

                                        @Override
                                        public boolean keyTyped(char character) {
                                            return false;
                                        }

                                        @Override
                                        public boolean touchDown(int screenX, int screenY, int pointer, int button) {

				/*
				if(button1.getX()*PPM==screenX){

					//horizontalForce -= speed;
					Gdx.app.log("Button","touchDown");


				} else if(button1.getY()*PPM==screenY){

					//horizontalForce +=speed;
					Gdx.app.log("Button","touchDown");


				}

				*/
                                            return false;
                                        }

                                        @Override
                                        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				/*
				if(button1.getX()==screenX){

					//horizontalForce = 0;
					Gdx.app.log("Button","touchup");


				} else if(button1.getY()==screenY){

					//horizontalForce +=0;
					Gdx.app.log("Button","touchup");

				}

				*/
                                            return false;
                                        }

                                        @Override
                                        public boolean touchDragged(int screenX, int screenY, int pointer) {
                                            return false;
                                        }

                                        @Override
                                        public boolean mouseMoved(int screenX, int screenY) {

                                            return false;
                                        }

                                        @Override
                                        public boolean scrolled(int amount) {
                                            return false;
                                        }




                                    }


        );

       /*
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){

			horizontalForce -= speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){

			horizontalForce +=speed;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.UP)){

			player.setLinearVelocity(player.getLinearVelocity().x , speed);
		}
		*/




        player.setLinearVelocity(player.getLinearVelocity().x , player.getLinearVelocity().y);


    }




    public void update(float delta) {

        world.step(1 / 60f, 6, 2);

        cameraupdate(delta);
        inputUpdate(delta);
        batch.setProjectionMatrix(camera.combined);                          //not using this for now
        shaperender.setProjectionMatrix(camera.combined);

    }






    public Body createplayer(int x , int y , float width, float height, boolean isStatic) {





        //body definition

        //------------player------//

        if (isStatic)
            bodyDef.type = BodyDef.BodyType.StaticBody;
        else
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PPM, y / PPM);
        //.fixedRotation = true;


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);          // taking bodies position
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.density = 0.0f;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef).getUserData();

        shape.dispose();






        //-----------platfrom-------------//
/*



		return body;

	}

*/
        return body;

    }


    public Body createEdge(int x ,int y ,float x1,float y1, boolean isStatic ){

        Body body1;

        if (isStatic)
            bodyDef.type = BodyDef.BodyType.StaticBody;
        else
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x/PPM,y/PPM);

        ChainShape shape1 = new ChainShape();
        shape1.createChain(new Vector2[] {new Vector2(-x1*PPM,y1*PPM) ,new Vector2(x1*PPM,y1*PPM)});
        fixtureDef.shape = shape1;
        fixtureDef.density = .7f;
        fixtureDef.friction = 1.0f;
        body1 = world.createBody(bodyDef);
        body1.createFixture(fixtureDef);
        shape1.dispose();

        return body1;



    }




    public Body destoryobj(int x , int y ,float width, float height, boolean isStatic) {


        //body definition

        //------------player------//

        if (isStatic)
            bodyDef.type = BodyDef.BodyType.StaticBody;
        else
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x / PPM, y / PPM);
        //.fixedRotation = true;


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / PPM, height / 2 / PPM);          // taking bodies position
        fixtureDef.shape = shape;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.density = 0.0f;


        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);


        shape.dispose();


        return body;

    }


    public void drawtex(Texture in , Body by , Batch ba){     // not using for now


        ba.begin();
        ba.draw(in , by.getPosition().x*PPM-(in.getWidth()/2),by.getPosition().y*PPM-(in.getHeight()/2));
        ba.end();

    }






    public void Colorchange(float r , float g , float b , float a) {

        // r = 0; g = 0.0f ; b = 0.0f; a = 1.0f;

        rayH.setAmbientLight(r, g, b, a);


    }


    public void cameraupdate(float delta) {                       // getting bodies position

        Vector3 position = camera.position;
        position.set(380,30,0);

        camera.position.set(position);
        camera.update();



    }



}


