/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controller.narrator;

import core.model.libro.audioBook.AudioBook;
import core.model.persona.narrator.Narrator;


public class NarratorAudioBookService {

    public static void assignNarration(Narrator narrator, AudioBook audioBook) {
        narrator.addAudioBook(audioBook);
    }
}
