package com.yokogawa.dispatchnotes.create;
import com.microsoft.playwright.Page;
public interface DispatchNoteCreateInterface {
    void DNCreate(String mailId, String sourceCountry, String destinationCountry, String packagetype, int grossweight, int netWeight, int volume, int quantity, Page page);
}
