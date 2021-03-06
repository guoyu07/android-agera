/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.codelabs.agera.step1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.example.android.codelabs.agera.R;
import com.google.android.agera.MutableRepository;
import com.google.android.agera.Repositories;
import com.google.android.agera.Updatable;

public class Step1Activity extends AppCompatActivity {
  private MutableRepository<String> mStringRepo;
  private Updatable mLoggerUpdatable;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.step1);
    mStringRepo = Repositories.mutableRepository("Initial value");
    mLoggerUpdatable = () -> Toast.makeText(this, mStringRepo.get(), Toast.LENGTH_SHORT).show();
  }

  @Override protected void onStart() {
    super.onStart();
    mStringRepo.addUpdatable(mLoggerUpdatable);

    // Change Repo value
    mStringRepo.accept("Hello World!!!");
  }

  @Override protected void onStop() {
    mStringRepo.removeUpdatable(mLoggerUpdatable);
    super.onStop();
  }
}
