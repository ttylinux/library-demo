/**
 * @author LuShuWei E-mail:albertxiaoyu@163.com
 * @version 创建时间：2014-11-22 上午10:48:02 类说明
 */

package com.library_demo.activity;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.library_demo.R;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.widget.TextView;

public class ActivityWithRichTextView extends Activity {

  private TextView richTextView;
  private Resources res;


  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.activity_with_richtextview_layout);
    inital();

  }

  private void inital() {
    richTextView = (TextView) findViewById(R.id.richTextView);
    res = getResources();
    setTextView(richTextView, res.getString(R.string.richtextview));
  }

  private void setTextView(TextView textView, String str) {

    SpannableString strWithStyle = new SpannableString(str);
    String digitPart = foundtheFirstDigitStr(str);
    if (digitPart.length() == 0) {
      textView.setText(str);
    } else {

      int start = str.indexOf(digitPart);
      int end = start + digitPart.length();

      strWithStyle.setSpan(new RelativeSizeSpan(2), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
      strWithStyle.setSpan(new ForegroundColorSpan(Color.parseColor("#122956")), start, end,
          Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
      strWithStyle.setSpan(new TypefaceSpan("sans-serif"), start, end,
          Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
      strWithStyle.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), start, end,
          Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

      textView.setText(strWithStyle);
    }

  }

  private String foundtheFirstDigitStr(String str) {
    String digitPattern = "\\d{3}";
    Matcher matcher = Pattern.compile(digitPattern).matcher(str);

    if (matcher.find()) {
      return matcher.group();
    } else {
      return "";
    }
  }
}
