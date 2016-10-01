/*
 * Copyright (c) 2016 On-Site.com.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this work. If not, see <http://www.gnu.org/licenses/>.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library.
 * If you modify this library, you may extend this exception to your
 * version of the library, but you are not obligated to do so. If you do
 * not wish to do so, delete this exception statement from your version.
 */

package com.sun.tools.hat.internal.server;

import com.google.common.collect.ImmutableList;

import java.util.List;

abstract class RedirectQueryHandler extends QueryHandler {
    @Override
    public String getHttpStatus() {
        return "302 Moved Temporarily";
    }

    @Override
    public List<String> getHeaders() {
        return ImmutableList.<String>builder()
                .addAll(super.getHeaders())
                .add("Location: " + getRedirectPath())
                .build();
    }

    @Override
    public void run() {
        process();
        out.println("<html><body>Your browser should have redirected you to: <a href=\"" + getRedirectPath() + "\">" + getRedirectPath() + "</a></body></html>");
    }

    public abstract void process();

    public abstract String getRedirectPath();
}