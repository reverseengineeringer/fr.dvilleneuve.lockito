package fr.dvilleneuve.lockito.ui;

import java.io.File;
import java.util.Comparator;

class SelectFileActivity$1
  implements Comparator<File>
{
  SelectFileActivity$1(SelectFileActivity paramSelectFileActivity) {}
  
  public int compare(File paramFile1, File paramFile2)
  {
    String str1 = paramFile1.getName();
    String str2 = paramFile2.getName();
    if ((paramFile1.isDirectory()) && (paramFile2.isDirectory())) {
      return str1.compareTo(str2);
    }
    if ((paramFile1.isDirectory()) && (paramFile2.isFile())) {
      return -1;
    }
    if ((paramFile1.isFile()) && (paramFile2.isDirectory())) {
      return 1;
    }
    return str1.compareTo(str2);
  }
}

/* Location:
 * Qualified Name:     fr.dvilleneuve.lockito.ui.SelectFileActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */