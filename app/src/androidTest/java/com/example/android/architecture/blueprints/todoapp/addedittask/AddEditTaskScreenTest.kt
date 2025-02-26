/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.architecture.blueprints.todoapp.addedittask

import androidx.compose.material3.Surface
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.TestActivity
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.TodoTheme
import com.example.android.architecture.blueprints.todoapp.data.TaskRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject

/**
 * Integration test for the Add Task screen.
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class AddEditTaskScreenTest : KoinTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestActivity>()
    private val activity get() = composeTestRule.activity

    private val repository: TaskRepository by inject()

    @Before
    fun setup() {
        // GIVEN - On the "Add Task" screen.
        composeTestRule.setContent {
            TodoTheme {
                Surface {
                    AddEditTaskScreen(
                        viewModel = get(),
                        topBarTitle = R.string.add_task,
                        onTaskUpdate = { },
                        onBack = { },
                    )
                }
            }
        }
    }

    @Test
    fun emptyTask_isNotSaved() {
        // WHEN - Enter invalid title and description combination and click save
        findTextField(R.string.title_hint).performTextClearance()
        findTextField(R.string.description_hint).performTextClearance()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.cd_save_task))
            .performClick()

        // THEN - Entered Task is still displayed (a correct task would close it).
        composeTestRule
            .onNodeWithText(activity.getString(R.string.empty_task_message))
            .assertIsDisplayed()
    }

    @Test
    fun validTask_isSaved() = runTest {
        // WHEN - Valid title and description combination and click save
        findTextField(R.string.title_hint).performTextInput("title")
        findTextField(R.string.description_hint).performTextInput("description")
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.cd_save_task))
            .performClick()

        // THEN - Verify that the repository saved the task
        val tasks = repository.getTasks(true)
        assertEquals(1, tasks.size)
        assertEquals("title", tasks[0].title)
        assertEquals("description", tasks[0].description)
    }

    private fun findTextField(text: Int): SemanticsNodeInteraction {
        return composeTestRule.onNode(
            hasSetTextAction() and hasText(activity.getString(text))
        )
    }
}
