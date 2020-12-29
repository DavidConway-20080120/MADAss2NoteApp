package com.madass2noteapp

import com.madass2noteapp.dataClasses.Group
import com.madass2noteapp.dataClasses.Note
import com.madass2noteapp.mainApp.MainApp
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Assert
import org.junit.Test

public class mainAppTest {
    @Test
    fun createGroupTest() {
        val testMainApp = MainApp()
        val testGroup = Group("test")
        testMainApp.createGroup(testGroup)
        assertEquals("test", testMainApp.allGroups[0].title)
    }

    @Test
    fun getGroupGoodTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        testMainApp.createGroup(testGroup)
        val testGet = testMainApp.getGroup(testGroup.title)
        assertEquals(testGroup, testGet)
    }

    @Test
    fun getGroupBadTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        testMainApp.createGroup(testGroup)
        val testGet = testMainApp.getGroup("something")
        assertNull(testGet)
    }

    @Test
    fun createNoteTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        val testNote = Note("test note")
        testMainApp.createNote(testGroup, testNote)
        assertEquals("test note", testGroup.notes[0].title)
    }

    @Test
    fun noteSwapTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        val testGroup2 = Group("test 2")
        val testNote = Note("test note")
        testMainApp.createNote(testGroup,testNote)
        testMainApp.noteSwap(testNote,testGroup,testGroup2)
        assertEquals(testNote,testGroup2.notes[0])
        assertEquals(0,testGroup.notes.size)
    }

    @Test
    fun getNoteGoodTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        val testNote = Note("test note")
        testMainApp.createNote(testGroup,testNote)
        val getNote = testMainApp.getNote(testGroup, "test note")
        assertEquals(testNote,getNote)
    }

    @Test
    fun getNoteBadTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        val testNote = Note("test note")
        testMainApp.createNote(testGroup,testNote)
        val getNote = testMainApp.getNote(testGroup, "something")
        assertNull(getNote)
    }
}