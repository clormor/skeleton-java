#!/bin/bash

SCRIPT_BASE=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )

LANG=C find $SCRIPT_BASE/../* -type f -exec sed -i '' "s/$1/$2/" {} \;

