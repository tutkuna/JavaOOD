General description of how our model works:

 After multiple iterations, we finally decided to take the approach of using KeyFrames. A KeyFrame
is a storage of the properties of a shape at a given key time, which can be used to
extrapolate the properties of the shape at the times in between the keyFrames. By using
KeyFrames, we are able to say what a shape will look like
at a specific key time. For example, we can say that at tick t = 10 a Rectangle R
(its id) has a specific set of values, x 200, y 200, w 50, h 100, and rgb of
(255,0,0) and at tick t = 50 that shape has gone up to 300 and 300 in x and y respectively and
otherwise has the same parameters. The motion would be simulated in the controller using the two key
frames' values and passed into the view to be visualized. Using the KeyFrame approach, we prevent
animations potentially overlapping because any keyframe added in between two keyFrames will change
the motion from a motion between the two keyframe to two motions (one between the first and the
newly added keyframe, one between the newly added and the second keyframe).


Each animation is constructed with the model implementation's static builder, and it is immutable
after construction. The view then takes in the model, an output source in the form of an
appendable if relevant (svg or text) and an int for ticks per second if relevant (svg or visual).
Calling the run method on the view outputs the animation formatted based on the view (visually,
in svg format, or in text format). The getters for our model (which is read only) either return
copies of the stored lists or readonly shapes. A Shape can now be constructed without any starting
keyFrame to help with model building.


---------------------------------------------------------------------------------------------------

*BROKEN DOWN BY PACKAGE*

model package:
-model(interface): The most changes were made to our model. This interface represents the model for
our EasyAnimator. It has the methods readAll, getIDs, getShape, getBounds, and getMax. We removed
the methods print, addShape, addKeyFrame, removeShape, and removeFrame from the interface. We did
this because we wanted to make our model completely immutable after construction, so we moved a lot
of these methods that we decided to keep to our implementation as either private methods or in the
our final/static builder class. The readAll method's purpose is return a list of all the shapes
stored in our model. The getIDS method, which we added this iteration, returns a copy of the ordered
list of the set of all ids stored, and is particularly useful for our run method in obtaining the
shapes we need to "create" in our different view implementations. The getShape method simply returns
the shape of the given id (and is read-only/cannot be mutated). The getBounds method returns the
bound stored in the model, for the given bound type. The getMax method returns the maximum
dimensions (this) instance of our anim spans.

-AnimModelImpl(class): The implementation of model, this class implements all the methods in our
model interface. Our AnimModelImpl previously only one field, HashMap<String, Shape> map. When
first instantiated, this HashMap is empty. This iteration, we added the fields for x, y, width, and
height, as we realized we would actually need to use them in our model implementation. Using our
methods, we are able to add shapes/frames (now as private methods), as well as view an ArrayList of
all the values in our HashMap at any time (a copy of it). Additionally, we are able to addMotions
and setBounds (both as private methods) to our model as needed.

We further changed our model by adding a builder class: We use it to construct each animation and
ensure it is immutable after construction.
-Builder class (located in AnimModelImpl/implements AnimModelImpl): We created this class because
with it we are able to separate the construction of a complex object from its representation and build
different objects using same construction process. This class has the methods build, setBounds,
declareShape, addMotion, and addKeyFrames. The build method simply returns the constructed model.
The setBounds method "sets the bounds" of our model and returns it. The declareShape method adds
the shape of the appropriate type to the model. The addMotion method allows us to add a motion to
our model, and the addKeyFrame allows us to add a keyFrame to our model.

---------------------------------------------------------------------------------------------------

keyframe:
-KeyFrame (interface): This interface represents how we store the properties of a shape at a given
key time, and is especially helpful because we can later utilize it to extrapolate the properties of
a shape at the times in between the keyFrames. It has the methods getX, getY, getWidth, getHeight,
and getColor.The getter methods are rather straight forward-- their purpose is to "get" the
properties of the shape. A change we made in this package was removing the print method as we no
longer found it necessary.

-KeyFrameImpl (class): The implementation of KeyFrame, this class implements all the methods in our
KeyFrame interface. It has the fields width, height, posX, posY, and col (color), which represent
the properties a shape would have at that particular keyFrame. We decided to make
these fields final so they would be protected from unwanted mutation. If the same shape was to have
different properties at a specific key time, we would replace the entire keyFrame
itself with the new one being given with those new properties. We don't disallow negative width or
height, as a negative width or height would imply that the shape has
flipped. A change we made in this implementation was adding more to our constructor to better
reflect our design choice of allowing negative width/height.

---------------------------------------------------------------------------------------------------

shapes:
*A shape is created with a single keyFrame assigned to the time passed in the constructor. However,
adding a new keyframe at a time prior to the time passed in through the constructor would move the
creation time to that keyframe, while the previous creation would just be another keyframe in its
sequence.

-Shape(interface): This interface represents our shapes in the animation. It has the methods
addFrame and removeFrame. We moved the methods getType, getFrame, getKeyTimes, and tween from our
Shape interface and into our ReadShape interface, to help prevent mutation of our shapes. We removed
the method printFrames altogether as we no longer found it necessary to have. The purpose of
addFrame is to add a frame at the given tick or replace it if one already exists at the given
tick. The purpose of the removeFrames method is to remove the keyFrame at the given time.  If you
remove all the keyFrames from a shape, the shape is essentially disabled until a new key frame is
added.

(newly added)
-ReadShape(interface): This interface is the "read only" interface for Shape and its getter methods.
It's purpose, as previously stated, is to prevent unwanted/unintentional mutation of the shapes.
It has the methods getType, getFrame, getKeyTimes, and tween. The purpose of the getType method is
to return the type of the shape as a string. The purpose of the getFrame method is to get the
keyFrame of "this" Shape at the given time. The purpose of the getKeyTimes method is to return an
(ordered) list of times where a Shape has a keyFrame. The purpose of the tween method is to get the
frame at the given time even if it isn't a keyFrame.


-AbstractShape(class): Implements the Shape interface/all its methods. AbstractShape is where we
abstract out most of the information for shapes, with the exception being their type which is
carried out in its child classes. AbstractShape has one field, Hashmap<Integer,
KeyFrame> frames, which is where we store all the properties of a shape at the time of its creation.
This iteration, we added a new constructor that creates a shape without any keyFrames and sets it
visible at creation. We did this because we believed it would be more useful in constructing our
animations. We also have a private method in this class that is a helper for the tween method, it's
where the math given to us is calculated.

-Ellipse(class): Extends AbstractShape. The only method that differs from the AbstractShape is the
getType method, which we simply return the string "ellipse" for.

-Rectangle(class): Extends AbstractShape. The only method that differs from the AbstractShape is the
 getType method, which we simply return the string "rectangle" for.

---------------------------------------------------------------------------------------------------
(All newly added)
view:

-AnimPanel(class): This class extends the JPanel class. It is a package-private helper class for
visual view. This class represents the panel for a visual view of the animation, as well as the
operations that help to facilitate this. These operations include paintComponent, draw, tick, and
done. We override paintComponent to draw every shape in the given model. The draw method draws the
given shape using graphics. The tick method advances the tick by one and repaints. The done method
checks if the animation is done.

-AniView(interface): This interface represents the operations offered by the AniView interface.
One object of an implementation of this interface would represent a type of viewing of the animation
(there are multiple types). It has one method, run, which "runs" the view and differs depending on
which implementation is calling it.

 -SVGView(class): Implements AniView interface. This class represents our SVG view for an animation
and the operations that help to facilitate this view. Its fields are the model, the appendable,
and milt(milliseconds per tick). It will throw an exception if the model/appendable are null, or if
TPS is less than or equal to zero-->  invariant, and will otherwise construct an SVG. The run
method saves the
animation into the given appendable in SVG format. It has the private helper methods printAnims,
printRect, printEllie (ellipse), printAnimHelp, and printRGB. The printAnim method concatenates all
the animations of the given shape. PrintRect/printEllie both print a single animation of a
rectangle/ellipse, respectively. PrintAnimHelp is a helper method for printAnims and concatenates
the descriptive words to match their corresponding animations in order to produce the string of an
animation in SVG format. printRGB is a helper method for printAnims and formats the RGB values into
SVG format.

 -TextView(class): Implements AniView interface. This class represents our text view for an
animation and the operations that help to facilitate this view. Its fields are the model and the
appendable. It will throw an exception if either are null and construct a text view otherwise.
The run method saves the animation into the given appendable in text format. It has the private
helper methods printFrames and printFrame. PrintFrames formats all the frames of the shape to a
String. PrintFrame, a helper method for printFrames, formats a single frame of a shape into a
string.

 -VisualView(class): Extends JFrame and implements AniView. This class represents a visual view for
the animation, and the operations that help to facilitate it. It has the fields panel (AnimPanel)
and tps(ticks per second). If the model passed in is null or the tps is less than or equal to
zero --> invariant, it will throw exception, and will construct a visual view otherwise. The panel
is instantiated as a new AnimPanel with the model passed in, and we set the preferred size by
calling our getMax method in our model. We create a new JScrollPanel that takes in our panel, and
add it to our visual view. We are then able to set our sizes, enable scrolling for our animation
(as needed), and have it exit on close (when the animation ends).The run method runs the visual
animation with ticking speed based on tps.













