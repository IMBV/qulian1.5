//package com.quliantrip.qulian.view;
//
//import android.annotation.TargetApi;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.os.Build;
//import android.support.v4.view.MotionEventCompat;
//import android.support.v4.view.ViewCompat;
//import android.support.v4.widget.ViewDragHelper;
//import android.util.AttributeSet;
//import android.view.Gravity;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.zbd.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Administrator on 2015-11-5.
// */
//public class BottomDrawerLayout extends ViewGroup{
//  private static final int[] LAYOUT_ATTRS = new int[]{android.R.attr.layout_gravity};
//  private ViewDragCallback mCallback;
//  private ViewDragHelper mDragger;
//  private DrawerListener mListener;
//  private View dragChild;
//  private boolean draggable;
//  private float friction;
//  @SuppressWarnings("unchecked")
//  private List<AbsVisible> cfG = new ArrayList();
//  private List<AbsVisible> cfH = new ArrayList();
//  //    private abw cfI;
//  private float cfJ;
//  private final ArrayList<View> mChildViews = new ArrayList(1);
//  private int cfL = -1728053248;
//  private Paint mPaint = new Paint();
//  public float playBackControlSize;
//  public float cfz;
//  private boolean mInLayout;
//  private float f1044;
//  private boolean f1045 = true;
//
//  class VisibleImpl extends AbsVisible {
//    final /* synthetic */ BottomDrawerLayout drawer;
//
//    VisibleImpl(BottomDrawerLayout o_bbd) {
//      this.drawer = o_bbd;
//    }
//
//    boolean FO() {
//      return this.ccX && this.view.getTop() >= this.drawer.getHeight();
//    }
//
//    boolean FP() {
//      return this.view.getTop() < this.drawer.getHeight();
//    }
//  }
//
//  public static class LayoutParams extends MarginLayoutParams {
//    float onScreen;
//    public int gravity = -1;
//
//    public LayoutParams(Context context, AttributeSet attributeSet) {
//      super(context, attributeSet);
//      TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, BottomDrawerLayout.LAYOUT_ATTRS);
//      this.gravity = obtainStyledAttributes.getInt(0, 0);
//      obtainStyledAttributes.recycle();
//    }
//
//    public LayoutParams(int i, int i2) {
//      super(i, i2);
//    }
//
//    public LayoutParams(LayoutParams c1522) {
//      super(c1522);
//    }
//
//    public LayoutParams(ViewGroup.LayoutParams layoutParams) {
//      super(layoutParams);
//    }
//
//    public LayoutParams(MarginLayoutParams marginLayoutParams) {
//      super(marginLayoutParams);
//    }
//  }
//
//  public interface DrawerListener {
//    void onDrawerSlide(View view, float f, int i, int i2, int i3, int i4);
//
//    void onDrawerStateChanged(View view, int i);
//
//    void onDrawerOpened(View view);
//
//    void onDrawerClosed(View view);
//  }
//
//
//  //自定义的viewDragCallBack的回调
//  class ViewDragCallback extends ViewDragHelper.Callback {
//    final /* synthetic */ BottomDrawerLayout btDrawer;
//
//    private ViewDragCallback(BottomDrawerLayout btDrawer) {
//      this.btDrawer = btDrawer;
//    }
//
//    public int getOrderedChildIndex(int i) {
//      for (int i2 = i; i2 >= 0; i2--) {
//        if (this.btDrawer.getChildAt(i2).getVisibility() != View.GONE) {
//          return i2;
//        }
//      }
//      return i;
//    }
//
//    public void onViewDragStateChanged(int i) {
//      if (this.btDrawer.mListener != null) {
//        this.btDrawer.mListener.onDrawerStateChanged(this.btDrawer.mDragger.getCapturedView(), i);
//      }
//    }
//
//    public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
//      float f = 0.0f;
//      switch (view.getId()) {
//        case R.id.drag_child:
//          f = ((float) i2) / this.btDrawer.playBackControlSize;
//          break;
//        default:
//          if (this.btDrawer.m6623(view)) {
//            f = ((float) (this.btDrawer.getHeight() - i2)) / ((float) view.getHeight());
//            break;
//          }
//          break;
//      }
//      if (this.btDrawer.mListener != null) {
//        this.btDrawer.mListener.onDrawerSlide(view, f, i, i2, i3, i4);
//      }
//      ((LayoutParams) view.getLayoutParams()).onScreen = f;
//      this.btDrawer.invalidate();
//    }
//
//    public int getViewVerticalDragRange(View view) {
//      switch (view.getId()) {
//        case R.id.drag_child:
//          return (int) this.btDrawer.playBackControlSize;
//        default:
//          if (this.btDrawer.m6623(view)) {
//            return view.getHeight();
//          }
//          return 0;
//      }
//    }
//
//    public int clampViewPositionVertical(View view, int i, int i2) {
//      switch (view.getId()) {
//        case R.id.drag_child:
//          if (((float) i) > this.btDrawer.playBackControlSize) {
//            return (int) this.btDrawer.playBackControlSize;
//          }
//          if (i < 0) {
//            return 0;
//          }
//          return view.getTop() + ((int) (((float) i2) * this.btDrawer.friction));
//        default:
//          if (!this.btDrawer.m6623(view)) {
//            return 0;
//          }
//          int height = this.btDrawer.getHeight();
//          return Math.max(height - view.getHeight(), Math.min(i, height));
//      }
//    }
//
//    public boolean tryCaptureView(View view, int pointerId) {
//      boolean z;
//      if (this.btDrawer.m6623(view)) {
//        z = (this.btDrawer.m6624(view) || view.canScrollVertically((int) (this.btDrawer.f1044 - this.btDrawer.cfJ))) ? false : true;
//        return z;
//      }
//      z = !this.btDrawer.m6624(view) && view == this.btDrawer.dragChild;
//      return z;
//    }
//
//    public void onViewReleased(View view, float xvel, float yvel) {
//      boolean z;
//      if (view == this.btDrawer.dragChild) {
//        Object obj = (yvel > 0.0f || (yvel == 0.0f && view.getTop() > ((int) this.btDrawer.cfz))) ? 1 : null;
//        this.btDrawer.mDragger.settleCapturedViewAt(view.getLeft(), obj != null ? (int) this.btDrawer.playBackControlSize : 0);
//        z = obj == null;
//      } else {
//        int height = this.btDrawer.getHeight();
//        boolean z2 = yvel > 0.0f || (yvel == 0.0f && view.getTop() > height / 2);
//        this.btDrawer.mDragger.settleCapturedViewAt(view.getLeft(), z2 ? height : height - view.getHeight());
//        z = z2;
//      }
//      this.btDrawer.invalidate();
//            /*if (z) {
//                if (this.btDrawer.cfI != null) {
//                    this.btDrawer.cfI.vD();
//                }
//            } else if (this.btDrawer.cfI != null) {
//                this.btDrawer.cfI.vC();
//            }*/
//      this.btDrawer.dispatchDrawerState(view, z);
//    }
//  }
//
//  protected /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
//    return Gt();
//  }
//
//  public /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
//    return m6625(attributeSet);
//  }
//
//  public BottomDrawerLayout(Context context) {
//    super(context);
//    init(context);
//  }
//
//  public BottomDrawerLayout(Context context, AttributeSet attributeSet) {
//    super(context, attributeSet);
//    init(context);
//  }
//
//  public BottomDrawerLayout(Context context, AttributeSet attributeSet, int i) {
//    super(context, attributeSet, i);
//    init(context);
//  }
//
//  private void init(Context context) {
//    this.mCallback = new ViewDragCallback(this);
//    this.friction = 1.0f;
////        this.playBackControlSize = context.getResources().getDimension(R.dimen.playback_control_size);
//    this.playBackControlSize = 0;
//    this.cfz = this.playBackControlSize / 2.0f;
//    float minVelocity = 400.0f * getResources().getDisplayMetrics().density;
//    this.mDragger = ViewDragHelper.create(this, 1.0f, this.mCallback);
//    this.mDragger.setMinVelocity(minVelocity);
//  }
//
//  public void setOnViewDragListener(DrawerListener listener) {
//    this.mListener = listener;
//  }
//
//  public void setDraggable(boolean draggable) {
//    this.draggable = draggable;
//  }
//
//    /*public void setDragRecorder(abw o_abw) {
//        this.cfI = o_abw;
//    }*/
//
//  public void setFriction(float f) {
//    this.friction = f;
//  }
//
//  protected void onFinishInflate() {
//    super.onFinishInflate();
//    this.dragChild = findViewById(R.id.drag_child);
//    m6614(R.id.bottom_drag_child, true, false);
//    m6614(R.id.action_sheet, false, true);
////        m6614(R.id.bottom_sheet, false, false);
//  }
//
//  private void m6614(int i, boolean z, boolean z2) {
//    View findViewById = findViewById(i);
//    if (findViewById != null) {
//      AbsVisible c1520if = new VisibleImpl(this);
//      c1520if.view = findViewById;
//      c1520if.ccX = z;
//      c1520if.ccY = z2;
//      this.cfH.add(c1520if);
//      this.cfG.add(c1520if);
//    }
//  }
//
//  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//    int i3;
//    int childCount = getChildCount();
//    Object obj = (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY && MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) ? null : 1;
//    this.mChildViews.clear();
//    int i4 = 0;
//    int i5 = 0;
//    int i6 = 0;
//    for (i3 = 0; i3 < childCount; i3++) {
//      View childAt = getChildAt(i3);
//      if (childAt.getVisibility() != View.GONE) {
//        measureChildWithMargins(childAt, widthMeasureSpec, 0, heightMeasureSpec, 0);
//        LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
//        i5 = Math.max(i5, (childAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin);
//        i4 = Math.max(i4, (childAt.getMeasuredHeight() + layoutParams.topMargin) + layoutParams.bottomMargin);
//        i6 = BottomDrawerLayout.combineMeasuredStates(i6, childAt.getMeasuredState());
//        if (obj != null && (layoutParams.width == -1 || layoutParams.height == -1)) {
//          this.mChildViews.add(childAt);
//        }
//      }
//    }
//    setMeasuredDimension(BottomDrawerLayout.resolveSizeAndState(Math.max(i5 + (getPaddingLeft() + getPaddingRight()), getSuggestedMinimumWidth()),
//        widthMeasureSpec, i6), BottomDrawerLayout.resolveSizeAndState(Math.max(i4 + (getPaddingTop() + getPaddingBottom()), getSuggestedMinimumHeight()), heightMeasureSpec, i6 << 16));
//    childCount = this.mChildViews.size();
//    if (childCount > 1) {
//      for (i3 = 0; i3 < childCount; i3++) {
//        int makeMeasureSpec;
//        int makeMeasureSpec2;
//        View child = this.mChildViews.get(i3);
//        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
//        if (marginLayoutParams.width == -1) {
//          makeMeasureSpec = MeasureSpec.makeMeasureSpec((((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - marginLayoutParams.leftMargin) - marginLayoutParams.rightMargin, MeasureSpec.EXACTLY);
//        } else {
//          makeMeasureSpec = BottomDrawerLayout.getChildMeasureSpec(widthMeasureSpec, ((getPaddingLeft() + getPaddingRight()) + marginLayoutParams.leftMargin) + marginLayoutParams.rightMargin, marginLayoutParams.width);
//        }
//        if (marginLayoutParams.height == -1) {
//          makeMeasureSpec2 = MeasureSpec.makeMeasureSpec((((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - marginLayoutParams.topMargin) - marginLayoutParams.bottomMargin, MeasureSpec.EXACTLY);
//        } else {
//          makeMeasureSpec2 = BottomDrawerLayout.getChildMeasureSpec(heightMeasureSpec, ((getPaddingTop() + getPaddingBottom()) + marginLayoutParams.topMargin) + marginLayoutParams.bottomMargin, marginLayoutParams.height);
//        }
//        child.measure(makeMeasureSpec, makeMeasureSpec2);
//      }
//    }
//  }
//
//  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//    this.mInLayout = true;
//    innerLayout(left, top, right, bottom, false);
//    Gq();
//    for (AbsVisible o_azg : this.cfH) {
//      handleLayout(o_azg.view, top, bottom);
//    }
//    this.mInLayout = false;
//    this.f1045 = false;
//  }
//
//  private void Gq() {
//    View view = this.dragChild;
//    if (view != null) {
//      LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
//      int i = (int) (this.playBackControlSize * layoutParams.onScreen);
//      view.layout(layoutParams.leftMargin, i, layoutParams.leftMargin + view.getMeasuredWidth(), i + view.getMeasuredHeight());
//    }
//  }
//
//  private void handleLayout(View view, int i, int i2) {
//    if (view != null) {
//      int i3 = i2 - i;
//      int measuredHeight = view.getMeasuredHeight();
//      int measuredWidth = view.getMeasuredWidth();
//      LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
//      int i4 = i3 - ((int) (((float) measuredHeight) * layoutParams.onScreen));
//      float f = ((float) (i3 - i4)) / ((float) measuredHeight);
//      Object obj = f != layoutParams.onScreen ? 1 : null;
//      view.layout(layoutParams.leftMargin, i4, layoutParams.leftMargin + measuredWidth, i4 + measuredHeight);
//      if (obj != null) {
//        layoutParams.onScreen = f;
//      }
//    }
//  }
//
//  private boolean m6623(View view) {
//    return m6609(this.cfH, view);
//  }
//
//  private boolean m6609(List<AbsVisible> list, View view) {
//    return m6611((List) list, view) != null;
//  }
//
//  private AbsVisible m6611(List<AbsVisible> list, View view) {
//    for (AbsVisible o_azg : list) {
//      if (o_azg.view == view) {
//        return o_azg;
//      }
//    }
//    return null;
//  }
//
//  @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
//  private void innerLayout(int left, int top, int right, int bottom, boolean changed) {
//    int childCount = getChildCount();
//    int paddingLeft = getPaddingLeft();
//    int paddingRight = (right - left) - getPaddingRight();
//    int paddingTop = getPaddingTop();
//    int paddingBottom = (bottom - top) - getPaddingBottom();
//    for (int i = 0; i < childCount; i++) {
//      View childView = getChildAt(i);
//      if (!(childView == this.dragChild || m6623(childView))) {
//        if (childView.getVisibility() != View.GONE) {
//          int i6;
//          int i7;
//          LayoutParams layoutParams = (LayoutParams) childView.getLayoutParams();
//          int measuredWidth = childView.getMeasuredWidth();
//          int measuredHeight = childView.getMeasuredHeight();
//          int i8 = layoutParams.gravity;
//          if (i8 == -1) {
//            i8 = 8388659;
//          }
//          int i9 = i8 & Gravity.VERTICAL_GRAVITY_MASK;
//          switch (Gravity.getAbsoluteGravity(i8, getLayoutDirection()) & Gravity.HORIZONTAL_GRAVITY_MASK) {
//            case Gravity.AXIS_SPECIFIED:
//              i6 = (((((paddingRight - paddingLeft) - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
//              break;
//            case Gravity.END /*5*/:
//              if (!changed) {
//                i6 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
//                break;
//              }
//            case Gravity.AXIS_PULL_BEFORE /*2*/:
//            case Gravity.START /*3*/:
//            case Gravity.AXIS_PULL_AFTER /*4*/:
//              i6 = paddingLeft + layoutParams.leftMargin;
//              break;
//            default:
//          }
//          i6 = paddingLeft + layoutParams.leftMargin;
//          switch (i9) {
//            case 16 /*16*/:
//              i7 = (((((paddingBottom - paddingTop) - measuredHeight) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
//              break;
//            case Gravity.TOP /*48*/:
//              i7 = paddingTop + layoutParams.topMargin;
//              break;
//            case Gravity.BOTTOM /*80*/:
//              i7 = (paddingBottom - measuredHeight) - layoutParams.bottomMargin;
//              break;
//            default:
//              i7 = paddingTop + layoutParams.topMargin;
//              break;
//          }
//          childView.layout(i6, i7, i6 + measuredWidth, i7 + measuredHeight);
//        }
//      }
//    }
//  }
//
//  public void requestLayout() {
//    if (!this.mInLayout) {
//      super.requestLayout();
//    }
//  }
//
//  protected void onDetachedFromWindow() {
//    super.onDetachedFromWindow();
//    this.f1045 = true;
//  }
//
//  protected void onAttachedToWindow() {
//    super.onAttachedToWindow();
//    this.f1045 = true;
//  }
//
//  public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
//    if (!this.draggable) {
//      return false;
//    }
//    int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
//    int pointerId = MotionEventCompat.getPointerId(motionEvent, 0);
//    float x = motionEvent.getX();
//    float y = motionEvent.getY();
//    this.cfJ = y;
//    boolean shouldInterceptTouchEvent = this.mDragger.shouldInterceptTouchEvent(motionEvent);
//    Object obj = null;
//    switch (actionMasked) {
//      case 0 /*0*/:
//        this.f1044 = y;
//        if (Gs() > 0.0f && this.mDragger.findTopChildUnder((int) x, (int) y) != Gr()) {
//          obj = 1;
//          break;
//        }
//      case 2 /*2*/:
//        View viewUnder = this.mDragger.findTopChildUnder((int) x, (int) y);
//        if (viewUnder == null || !viewUnder.canScrollVertically((int) (this.f1044 - y))) {
//          m6618(pointerId, y);
//        }
//        this.f1044 = y;
//        break;
//      default:
//        break;
//    }
//    return shouldInterceptTouchEvent || obj != null;
//  }
//
//  private View Gr() {
//    for (AbsVisible o_azg : this.cfG) {
//      if (o_azg.ccY && ((LayoutParams) o_azg.view.getLayoutParams()).onScreen > 0.0f) {
//        return o_azg.view;
//      }
//    }
//    return null;
//  }
//
//  private float Gs() {
//    View Gr = Gr();
//    if (Gr != null) {
//      return ((LayoutParams) Gr.getLayoutParams()).onScreen;
//    }
//    return 0.0f;
//  }
//
//  public boolean onTouchEvent(MotionEvent motionEvent) {
//    if (!this.draggable) {
//      return false;
//    }
//    float x = motionEvent.getX();
//    float y = motionEvent.getY();
//    this.cfJ = y;
//    try {
//      this.mDragger.processTouchEvent(motionEvent);
//    } catch (Exception e) {
//      if(e instanceof IllegalArgumentException) {
//
//      }
//      if(e instanceof IllegalArgumentException) {
//
//      }
//    }
//    View Gr;
//    switch (MotionEventCompat.getActionMasked(motionEvent)) {
//      case 0 /*0*/:
//        this.f1044 = motionEvent.getY();
//        break;
//      case 1 /*1*/:
//        Gr = Gr();
//        if (Gr != null) {
//          closeDrawer(Gr);
//          break;
//        }
//        break;
//      case 2 /*2*/:
//        Gr = this.mDragger.findTopChildUnder((int) x, (int) y);
//        if (Gr == null || !Gr.canScrollVertically((int) (this.f1044 - y))) {
//          m6618(MotionEventCompat.getPointerId(motionEvent, 0), y);
//        }
//        this.f1044 = y;
//        break;
//      default:
//        break;
//    }
//    return true;
//  }
//
//  private boolean m6624(View view) {
//    for (AbsVisible o_azg : this.cfG) {
//      if (view != o_azg.view) {
//        if (isDrawerOpened(o_azg.view)) {
//          return true;
//        }
//      }
//    }
//    return false;
//  }
//
//  private void m6618(int i, float f) {
//    if (!this.mDragger.checkTouchSlop(2, i) || this.mDragger.getCapturedView() != null) {
//      return;
//    }
//    View ᵔ;
//    if (f - this.f1044 > 0.0f) {
//      if (this.dragChild == null || this.dragChild.getTop() > 0 || m6624(this.dragChild)) {
//        ᵔ = m6622(this.cfH);
//        if (!(ᵔ == null || m6624(ᵔ))) {
//          this.mDragger.captureChildView(ᵔ, i);
//        }
//        return;
//      }
//      this.mDragger.captureChildView(this.dragChild, i);
//    } else if (this.dragChild == null || this.dragChild.getTop() <= 0 || m6624(this.dragChild)) {
//      ᵔ = m6621(this.cfH);
//      if (ᵔ != null && !m6624(ᵔ)) {
//        this.mDragger.captureChildView(ᵔ, i);
//      }
//    } else {
//      this.mDragger.captureChildView(this.dragChild, i);
//    }
//  }
//
//  private View m6621(List<AbsVisible> list) {
//    for (AbsVisible o_azg : list) {
//      if (o_azg.m6517(-1)) {
//        return o_azg.view;
//      }
//    }
//    return null;
//  }
//
//  private View m6622(List<AbsVisible> list) {
//    for (AbsVisible o_azg : list) {
//      if (o_azg.m6517(1)) {
//        return o_azg.view;
//      }
//    }
//    return null;
//  }
//
//  public void computeScroll() {
//    if (this.mDragger.continueSettling(true)) {
//      ViewCompat.postInvalidateOnAnimation(this);
//    }
//  }
//
//  protected LayoutParams Gt() {
//    return new LayoutParams(-1, -1);
//  }
//
//  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
//    if (layoutParams instanceof LayoutParams) {
//      return new LayoutParams((LayoutParams) layoutParams);
//    }
//    return layoutParams instanceof MarginLayoutParams ? new LayoutParams((MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
//  }
//
//  protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
//    return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
//  }
//
//  public LayoutParams m6625(AttributeSet attributeSet) {
//    return new LayoutParams(getContext(), attributeSet);
//  }
//
//  public boolean isDrawerOpened(View view) {
//    if (view == null) {
//      return false;
//    }
//    return ((LayoutParams) view.getLayoutParams()).onScreen > 0.0f;
//  }
//
//  public void openDrawer(View view) {
//    if (!this.f1045) {
//      switch (view.getId()) {
//        case R.id.drag_child:
//          this.mDragger.smoothSlideViewTo(view, view.getLeft(), (int) this.playBackControlSize);
//          break;
//        default:
//          if (m6623(view)) {
//            this.mDragger.smoothSlideViewTo(view, view.getLeft(), getHeight() - view.getHeight());
//            break;
//          }
//          throw new IllegalArgumentException("Not a drag child");
//      }
//    } else if (view.getId() == R.id.drag_child || m6609(this.cfG, view)) {
//      ((LayoutParams) view.getLayoutParams()).onScreen = 1.0f;
//    } else {
//      throw new IllegalArgumentException("Not a drag child");
//    }
//    invalidate();
//    dispatchDrawerState(view, false);
//  }
//
//  public void closeDrawer(View view) {
//    if (!this.f1045) {
//      switch (view.getId()) {
//        case R.id.drag_child:
//          this.mDragger.smoothSlideViewTo(view, view.getLeft(), 0);
//          break;
//        default:
//          if (m6623(view)) {
//            this.mDragger.smoothSlideViewTo(view, view.getLeft(), getHeight());
//            break;
//          }
//          throw new IllegalArgumentException("Not a drag child");
//      }
//    } else if (view.getId() == R.id.drag_child || m6609(this.cfG, view)) {
//      ((LayoutParams) view.getLayoutParams()).onScreen = 0.0f;
//    } else {
//      throw new IllegalArgumentException("Not a drag child");
//    }
//    invalidate();
//    dispatchDrawerState(view, true);
//  }
//
//  public void m6629(View view) {
//    switch (view.getId()) {
//      case R.id.drag_child:
//        if (view.getTop() <= 0) {
//          openDrawer(view);
//          return;
//        } else {
//          closeDrawer(view);
//          return;
//        }
//      default:
//        if (!m6623(view)) {
//          throw new IllegalArgumentException("Not a drag child");
//        } else if (view.getTop() >= getHeight()) {
//          openDrawer(view);
//          return;
//        } else {
//          closeDrawer(view);
//          return;
//        }
//    }
//  }
//
//  private void dispatchDrawerState(View view, boolean z) {
//    if (this.mListener == null) {
//      return;
//    }
//    if (z) {
//      this.mListener.onDrawerClosed(view);
//    } else {
//      this.mListener.onDrawerOpened(view);
//    }
//  }
//
//  protected boolean drawChild(Canvas canvas, View view, long j) {
//    boolean drawChild = super.drawChild(canvas, view, j);
//    AbsVisible ˋ = m6611(this.cfG, view);
//    if (ˋ != null && ˋ.ccY) {
//      float f = ((LayoutParams) view.getLayoutParams()).onScreen;
//      if (f > 0.0f) {
//        this.mPaint.setColor((((int) (((float) ((this.cfL & -16777216) >>> 24)) * f)) << 24) | (this.cfL & 16777215));
//        canvas.drawRect(0.0f, 0.0f, (float) getWidth(), (float) view.getTop(), this.mPaint);
//      }
//    }
//    return drawChild;
//  }
//
//}
