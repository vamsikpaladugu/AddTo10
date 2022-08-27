package com.vamsi.addtoten

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PlayActivity : AppCompatActivity(), View.OnLongClickListener {

    var currentExpression = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)


        //Text view
        val tvZero = findViewById<TextView>(R.id.tvZero)
        val tvOne = findViewById<TextView>(R.id.tvOne)
        val tvTwo = findViewById<TextView>(R.id.tvTwo)
        val tvThree = findViewById<TextView>(R.id.tvThree)
        val tvFour = findViewById<TextView>(R.id.tvFour)
        val tvFive = findViewById<TextView>(R.id.tvFive)

        val tvSix = findViewById<TextView>(R.id.tvSix)
        val tvSeven = findViewById<TextView>(R.id.tvSeven)
        val tvEight = findViewById<TextView>(R.id.tvEight)
        val tvNine = findViewById<TextView>(R.id.tvNine)
        val tvTen = findViewById<TextView>(R.id.tvTen)
        val tvEleven = findViewById<TextView>(R.id.tvLeven)

        val tvTwelve = findViewById<TextView>(R.id.tvTwelve)
        val tvThreeTeen = findViewById<TextView>(R.id.tvThirteen)
        val tvFourteen = findViewById<TextView>(R.id.tvFourteen)
        val tvAnswer = findViewById<TextView>(R.id.tvAnswer)


        val tvPlus = findViewById<TextView>(R.id.tvPlus)
        val tvMinus = findViewById<TextView>(R.id.tvMinus)
        val tvInto = findViewById<TextView>(R.id.tvMultiply)
        val tvDivided = findViewById<TextView>(R.id.tvDivided)
        val tvOpenBracket = findViewById<TextView>(R.id.openBracket)
        val tvClosedBracket = findViewById<TextView>(R.id.closeBracket)


        reset(null);


        tvThree.setOnDragListener(maskDropListener)
        tvSeven.setOnDragListener(maskDropListener)
        tvEleven.setOnDragListener(maskDropListener)


        tvPlus.setOnLongClickListener(this);
        tvMinus.setOnLongClickListener(this);
        tvInto.setOnLongClickListener(this);
        tvDivided.setOnLongClickListener(this);

        tvOpenBracket.setOnLongClickListener(this);
        tvClosedBracket.setOnLongClickListener(this);


        tvPlus.setOnDragListener(maskDragListener);
        tvMinus.setOnDragListener(maskDragListener);
        tvInto.setOnDragListener(maskDragListener);
        tvDivided.setOnDragListener(maskDragListener);


    }

    fun setSymbolsVisible(isVisible:Int) {


        val tvThree = findViewById<TextView>(R.id.tvThree)
        val tvSeven = findViewById<TextView>(R.id.tvSeven)
        val tvLeven = findViewById<TextView>(R.id.tvLeven)

        if (tvThree.text.isEmpty()) {
            tvThree.visibility = isVisible
        } else{
            tvThree.visibility = View.VISIBLE
        }

        if (tvSeven.text.isEmpty()) {
            tvSeven.visibility = isVisible
        } else{
            tvSeven.visibility = View.VISIBLE
        }

        if (tvLeven.text.isEmpty()) {
            tvLeven.visibility = isVisible
        } else{
            tvLeven.visibility = View.VISIBLE
        }


    }

    fun setBackgroundColor(color: Int) {

        findViewById<TextView>(R.id.tvThree).setBackgroundColor(color)
        findViewById<TextView>(R.id.tvSeven).setBackgroundColor(color)
        findViewById<TextView>(R.id.tvLeven).setBackgroundColor(color)

    }

    class MyDragShadowBuilder(v: View) : View.DragShadowBuilder(v) {

        private val shadow = ColorDrawable(Color.LTGRAY)

        // Defines a callback that sends the drag shadow dimensions and touch point
        // back to the system.
        override fun onProvideShadowMetrics(size: Point, touch: Point) {

            // Set the width of the shadow to half the width of the original View.
            val width: Int = view.width

            // Set the height of the shadow to half the height of the original View.
            val height: Int = view.height / 2

            // The drag shadow is a ColorDrawable. This sets its dimensions to be the
            // same as the Canvas that the system provides. As a result, the drag shadow
            // fills the Canvas.
            shadow.setBounds(0, 0, width, height)

            // Set the size parameter's width and height values. These get back to
            // the system through the size parameter.
            size.set(width, height)

            // Set the touch point's position to be in the middle of the drag shadow.
            touch.set(width, height)
        }

        // Defines a callback that draws the drag shadow in a Canvas that the system
        // constructs from the dimensions passed to onProvideShadowMetrics().
        override fun onDrawShadow(canvas: Canvas) {

            // Draw the ColorDrawable on the Canvas passed in from the system.
            shadow.draw(canvas)
        }

    }

    fun reset(view: View?) {

        val exp = String.format("%04d", GeneratePossibility().generateExpression());

        currentExpression = exp

        System.out.println("hello "+exp)


        findViewById<TextView>(R.id.tvThree).text = ""
        findViewById<TextView>(R.id.tvSeven).text = ""
        findViewById<TextView>(R.id.tvLeven).text = ""

        findViewById<TextView>(R.id.tvOne).text = exp[0].toString()
        findViewById<TextView>(R.id.tvFive).text = exp[1].toString()
        findViewById<TextView>(R.id.tvNine).text = exp[2].toString()
        findViewById<TextView>(R.id.tvThirteen).text = exp[3].toString()

        findViewById<TextView>(R.id.tvAnswer).text = "?"

        setSymbolsVisible(View.GONE);

    }



    private val maskDragListener = View.OnDragListener { view, dragEvent ->

        when (dragEvent.action) {

            DragEvent.ACTION_DRAG_STARTED -> {
                if (dragEvent.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // As an example of what your application might do, applies a blue color tint
                    // to the View to indicate that it can accept data.
                    (view as? TextView)?.setTextColor(Color.BLUE)

                    setSymbolsVisible(View.VISIBLE)
                    setBackgroundColor(Color.DKGRAY)

                    // Invalidate the view to force a redraw in the new tint.
                    view.invalidate()

                    // Returns true to indicate that the View can accept the dragged data.
                    true
                } else {
                    // Returns false to indicate that, during the current drag and drop operation,
                    // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                    false
                }
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                // Applies a green tint to the View.
                (view as? TextView)?.setTextColor(Color.BLUE)

                setSymbolsVisible(View.VISIBLE)
                setBackgroundColor(Color.DKGRAY)
                // Invalidates the view to force a redraw in the new tint.
                view.invalidate()

                // Returns true; the value is ignored.
                true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                // Ignore the event.
                true
            DragEvent.ACTION_DRAG_EXITED -> {
                // Resets the color tint to blue.
                (view as? TextView)?.setTextColor(Color.BLUE)

                setSymbolsVisible(View.VISIBLE)
                setBackgroundColor(Color.DKGRAY)

                // Invalidates the view to force a redraw in the new tint.
                view.invalidate()

                // Returns true; the value is ignored.
                true
            }
            DragEvent.ACTION_DROP -> {
                // Gets the item containing the dragged data.
                val item: ClipData.Item = dragEvent.clipData.getItemAt(0)

                // Gets the text data from the item.
                val dragData = item.text

                // Displays a message containing the dragged data.
                Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()

                // Turns off any color tints.
                (view as? TextView)?.setTextColor(Color.DKGRAY)

                setSymbolsVisible(View.VISIBLE)
                setBackgroundColor(Color.DKGRAY)


                // Invalidates the view to force a redraw.
                view.invalidate()

                // Returns true. DragEvent.getResult() will return true.
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                // Turns off any color tinting.
                (view as? TextView)?.setTextColor(Color.DKGRAY)

                setSymbolsVisible(View.GONE)
                setBackgroundColor(Color.TRANSPARENT)


                // Invalidates the view to force a redraw.
                view.invalidate()

                true
            }
            else -> {
                // An unknown action type was received.
                Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.")
                false
            }


        }

        true

    };


    private val maskDropListener = View.OnDragListener { view, dragEvent ->

        when (dragEvent.action) {

            DragEvent.ACTION_DROP -> {
                // Gets the item containing the dragged data.
                val item: String = dragEvent.clipData.getItemAt(0).text.toString()
                view.setBackgroundColor(Color.TRANSPARENT)

                val dragData = item

                (view as TextView).text = dragData

                // Displays a message containing the dragged data.
                Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_LONG).show()

                // Turns off any color tints.

                // Invalidates the view to force a redraw.
                view.invalidate()
                setSymbolsVisible(View.GONE)

                validateExpression();

                // Returns true. DragEvent.getResult() will return true.
                true
            }

            else -> {
                // An unknown action type was received.
                Log.e(
                    "DragDrop Example",
                    "Unknown action type received by View.OnDragListener."
                )
                false
            }


        }

        true

    };

    private fun validateExpression() {


        val tvThree = findViewById<TextView>(R.id.tvThree)
        val tvSeven = findViewById<TextView>(R.id.tvSeven)
        val tvLeven = findViewById<TextView>(R.id.tvLeven)

        val tvAnswer = findViewById<TextView>(R.id.tvAnswer)

        if(tvThree.text.isEmpty() || tvSeven.text.isEmpty() || tvLeven.text.isEmpty()) {
            tvAnswer.text = "?"
            return
        }


        val expression =
        findViewById<TextView>(R.id.tvOne).text.toString().trim()
            .plus(findViewById<TextView>(R.id.tvThree).text.toString().trim())

            .plus(findViewById<TextView>(R.id.tvFive).text.toString().trim())
            .plus(findViewById<TextView>(R.id.tvSeven).text.toString().trim())

            .plus(findViewById<TextView>(R.id.tvNine).text.toString().trim())
            .plus(findViewById<TextView>(R.id.tvLeven).text.toString().trim())
            .plus(findViewById<TextView>(R.id.tvThirteen).text.toString().trim())

        System.out.println("exp = "+expression)

        try {
            tvAnswer.text = EvaluateExpression().computeExpression(expression).toString()
        } catch (e:Exception) {
            tvAnswer.text = "?"
        }


    }


    override fun onLongClick(v: View?): Boolean {

        val item = ClipData.Item((v as TextView).text)
        val dragData = ClipData(
            v.tag as? CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            item
        )

        val myShadow = MyDragShadowBuilder(v)

        v.startDragAndDrop(
            dragData,  // The data to be dragged
            myShadow,  // The drag shadow builder
            null,      // No need to use local data
            0          // Flags (not currently used, set to 0)
        )

        return true
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.ans_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.ans -> {
                val int = Intent(this,MainActivity::class.java);
                int.putExtra("exp",Integer.parseInt(currentExpression))
                startActivity(int)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}