for /l %%i in (1,1,8) do (
        for /l %%j in (1,,1,8) do (
            start cmd.exe @cmd /k "java BuoyController "%%i" "%%j""
            )
        )
