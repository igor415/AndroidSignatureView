
# AndroidSignatureView

AndroidSignatureView is a library which allows users to draw a signature on specific view element.

## Features

- get signature in bitmap format
- set outline color and width
- show and customize description text
- customize background and signature colors


## Installation

Latest version of the library can be found on JitPack.

Step 1. Add the JitPack repository to your build file
```bash
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency to build.gradle 
```
dependencies {
	        implementation 'com.github.igor415:AndroidSignatureView:1.0.1'
	}

```

**For Maven users**
```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

```

```
	<dependency>
	    <groupId>com.github.igor415</groupId>
	    <artifactId>AndroidSignatureView</artifactId>
	    <version>1.0.1</version>
	</dependency>
```

    
## Usage/Examples

Step 1. Add the SignaturePad view to the layout you want to show.

```javascript
<com.varivoda.igor.signature.SignatureView
        android:id="@+id/signature"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        />
```
Step 2. Configure attributes

```setSignatureColor``` - change signature color

```setOutlineColor``` - change outline color

```setOutlineWidth``` - change outline width

```setDescriptionText``` - set or hide description text

```clearSignature``` - clear signature from view

Step 3. Get signature data

```getBitmapOrNullIfEmpty() ``` - returns Bitmap?

## License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```