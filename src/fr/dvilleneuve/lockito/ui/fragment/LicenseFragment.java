package fr.dvilleneuve.lockito.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.widget.TextView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EFragment(2130903080)
@OptionsMenu({2131755014})
public class LicenseFragment
  extends AbstractFragment
{
  @ViewById
  TextView content;
  @FragmentArg
  String contentString;
  @FragmentArg
  String urlString;
  
  @AfterViews
  void init()
  {
    setContent(contentString);
  }
  
  @OptionsItem
  void menuGoto()
  {
    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(urlString)));
  }
  
  void setContent(String paramString)
  {
    TextView localTextView = content;
    if (paramString != null) {}
    for (paramString = Html.fromHtml(paramString);; paramString = null)
    {
      localTextView.setText(paramString);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.fragment.LicenseFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */