package com.madass2noteapp

import androidx.test.platform.app.InstrumentationRegistry
import com.madass2noteapp.dataClasses.Group
import com.madass2noteapp.dataClasses.Note
import com.madass2noteapp.mainApp.MainApp
import org.junit.Assert
import org.junit.Test

class mainAppTest {
    @Test
    fun createGroupTest() {
        val testMainApp = MainApp()
        val testGroup = Group("test")
        testMainApp.createGroup(testGroup)
        Assert.assertEquals("title", testMainApp.allGroups[0].title)
    }

    @Test
    fun getGroupGoodTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        testMainApp.createGroup(testGroup)
        val testGet = testMainApp.getGroup(testGroup.title)
        Assert.assertEquals(testGroup, testGet)
    }

    @Test
    fun getGroupBadTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        testMainApp.createGroup(testGroup)
        val testGet = testMainApp.getGroup("something")
        Assert.assertNull(testGet)
    }

    @Test
    fun createNoteTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        val testNote = Note("test note")
        testMainApp.createNote(testGroup, testNote)
        Assert.assertEquals("test Note", testGroup.notes[0].title)
    }

    @Test
    fun noteSwapTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        val testGroup2 = Group("test 2")
        val testNote = Note("test note")
        testMainApp.createNote(testGroup,testNote)
        testMainApp.noteSwap(testNote,testGroup,testGroup2)
        Assert.assertEquals(testNote,testGroup2.notes[0])
        Assert.assertNull(testGroup.notes[0])
    }

    @Test
    fun getNoteGoodTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        val testNote = Note("test note")
        testMainApp.createNote(testGroup,testNote)
        val getNote = testMainApp.getNote(testGroup, "test note")
        Assert.assertEquals(testNote,getNote)
    }

    @Test
    fun getNoteBadTest(){
        val testMainApp = MainApp()
        val testGroup = Group("test")
        val testNote = Note("test note")
        testMainApp.createNote(testGroup,testNote)
        val getNote = testMainApp.getNote(testGroup, "something")
        Assert.assertNull(getNote)
    }
}